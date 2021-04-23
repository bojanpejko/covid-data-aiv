package com.aiv.covid.MDB;

import com.aiv.covid.dao.RegionDao;
import com.aiv.covid.observer.ObserverInterface;
import com.aiv.covid.observer.ObserverType;
import com.aiv.covid.observer.observers.MailObserver;
import com.aiv.covid.vao.InfectedData;
import com.aiv.covid.vao.Region;
import com.aiv.covid.vao.RegionAdministrator;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.ws.rs.NotFoundException;
import java.util.Calendar;
import java.util.UUID;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/topic/test"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
@Slf4j
public class MessageDrivenTopic implements MessageListener {

    @EJB
    RegionDao regionBean;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage) message;
                log.info("[AIVMessageDrivenTopic] MESSAGE HAS BEEN RECEIVED");

                if (mapMessage.getString("action").equals("addData")) {
                    Region region = regionBean.getByID(UUID.fromString(mapMessage.getString("region"))).orElseThrow();
                    InfectedData data = new InfectedData();

                    data.setUuid(UUID.randomUUID());
                    data.setInfected(Integer.parseInt(mapMessage.getString("infected")));
                    data.setHospitalized(Integer.parseInt(mapMessage.getString("hospitalized")));
                    data.setTested(Integer.parseInt(mapMessage.getString("tested")));
                    data.setDay(Calendar.getInstance());
                    data.setRegion(region);

                    region.addDailyData(data);
                    region.redefineZoneStatus();

                    RegionAdministrator regionAdministrator = regionBean.getAdminByID(region.getAdminID()).orElseThrow(() -> new NotFoundException("Admin not found"));

                    for(ObserverInterface observer : region.getObservers()){

                        if(observer instanceof MailObserver){
                            ((MailObserver) observer).setFrom(regionAdministrator.getEmail());
                            ((MailObserver) observer).setTo(regionAdministrator.getEmail());
                            ((MailObserver) observer).setMailSubject("Podatke dodane!");
                        }

                    }
                    region.notifyObservers(ObserverType.ADD);

                    regionBean.save(region);

                    log.info("[AIVMessageDrivenTopic] Data: " + data.toString());
                    log.info("[AIVMessageDrivenTopic] Added for region: " + region.getUuid());
                } else {
                    log.info("[AIVMessageDrivenTopic] ACTION IS NOT VERIFIED");
                }
            } else {
                log.info("[AIVMessageDrivenTopic] UNKNOWN MESSAGE HAS BEEN RECEIVED");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
