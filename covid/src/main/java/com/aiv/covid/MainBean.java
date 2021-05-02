package com.aiv.covid;

import com.aiv.covid.dao.DataDao;
import com.aiv.covid.dao.RegionDao;
import com.aiv.covid.dialog.DataDialogManager;
import com.aiv.covid.iterator.DataRepository;
import com.aiv.covid.iterator.Iterator;
import com.aiv.covid.observer.ObserverInterface;
import com.aiv.covid.observer.ObserverType;
import com.aiv.covid.observer.observers.MailObserver;
import com.aiv.covid.vao.InfectedData;
import com.aiv.covid.vao.Region;
import com.aiv.covid.vao.RegionAdministrator;
import com.aiv.covid.vao.ZoneStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.NotFoundException;
import java.io.Serializable;
import java.util.*;

@Slf4j
@Named("demo")
@SessionScoped
public class MainBean implements Serializable {

    private static final long serialVersionUID = -8979220536758073133L;

    @EJB
    RegionDao regionBean;

    @EJB
    DataDao dataBean;

    @Getter
    @Setter
    private RegionAdministrator currentAdmin = RegionAdministrator.getInstance();

    @Getter
    @Setter
    private Region tempRegion = new Region();

    @Getter
    @Setter
    private InfectedData tempData = new InfectedData();

    @Getter
    @Setter
    private List<Region> adminRegions = Collections.synchronizedList(new ArrayList<>());

    @Getter
    @Setter
    private List<InfectedData> allData = Collections.synchronizedList(new ArrayList<>());

    @Getter
    @Setter
    private String option = "0";

    @Getter
    @Setter
    private String zoneStatus;

    private final DataDialogManager dialogManager = DataDialogManager.getInstance();

    @PostConstruct
    public void init(){
        syncDataContainer();
    }

    private void syncRegionContainer(){ adminRegions = regionBean.getByAdminID(currentAdmin.getUuid()); }

    public void addRegion(){
        regionBean.saveAdmin(currentAdmin);
        tempRegion.setAdminID(currentAdmin.getUuid());

        regionBean.save(tempRegion);

        syncRegionContainer();
        syncDataContainer();
    }

    public void setTempRegionByID(UUID uuid){
        tempRegion = regionBean.getByID(uuid).orElseThrow(() -> new NotFoundException("Region not found."));

        tempRegion.getCalendar().size();

        if(tempRegion.getCalendar().isEmpty()){
            tempRegion.notifyObservers(ObserverType.NODATA);
        }
    }

    public void updateRegion(){
        regionBean.save(tempRegion);

        syncRegionContainer();
    }

    public void deleteRegion(){
        regionBean.delete(tempRegion.getUuid());

        tempRegion = new Region();
        syncRegionContainer();
    }

    public void addData(){
        tempData.setRegion(tempRegion);
        tempRegion.addDailyData(tempData);
        tempRegion.redefineZoneStatus();
        preAddData();

        regionBean.save(tempRegion);

        tempRegion.notifyObservers(ObserverType.ADD);
        syncRegionContainer();
        syncDataContainer();

        dialogManager.generateDialog("cloneDialog", false);
    }

    public void setTempDataByID(UUID uuid){
        tempData = dataBean.getByID(uuid).orElseThrow(() -> new NotFoundException("Data not found"));
        tempData.getRegion();
    }

    public void updateData(){
        dataBean.save(tempData);
        syncDataContainer();
    }

    public void deleteData(UUID uuid){
        dataBean.delete(uuid);

        tempData = new InfectedData();

        if(tempRegion.getCalendar().isEmpty()){
            tempRegion.notifyObservers(ObserverType.NODATA);
        }

        syncDataContainer();
    }

    public void preAddData(){
        for (ObserverInterface observer : tempRegion.getObservers()){
            if(observer instanceof MailObserver){
                ((MailObserver) observer).setTo(currentAdmin.getEmail());
                ((MailObserver) observer).setFrom(currentAdmin.getEmail());
                ((MailObserver) observer).setMailSubject("Sprememba podatkov!");
            }
        }
    }

    public void cloneData(InfectedData data){
        tempData = data.deepCopy();
        dialogManager.generateDialog("cloneDialog", true);
    }

    public void syncDataContainer(){
        switch(Integer.parseInt(option)){
            case 1:
                filterByDate(new DataRepository(dataBean.getAll()));
                option = "0";
                break;
            case 2:
                filterByPercentage();
                option = "0";
                break;
            case 3:
                filterByZone(ZoneStatus.valueOf(zoneStatus));
                option = "0";
                break;
            default:
                allData = dataBean.getAll();
                break;
        }
    }

    private void filterByDate(DataRepository dataRepository){
        allData.clear();

        for(Iterator itr = dataRepository.getIterator(); itr.hasNext();){
            InfectedData temp = itr.next();

            if(temp.getDay().get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)){
                if(temp.getDay().get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR)){
                    allData.add(temp);
                }
            }
        }
    }

    private void filterByPercentage(){
        DataRepository dataRepository = new DataRepository(dataBean.getAll());
        allData.clear();

        for(Iterator itr = dataRepository.getIterator(); itr.hasNext();){
            InfectedData temp = itr.next();
            double percentage = ((double) temp.getInfected() / (double) temp.getTested()) * 100;

            if(percentage <= 10.0){
                allData.add(temp);
            }
        }

        //filterByDate(new DataRepository(allData));
    }

    public void filterByZone(ZoneStatus status){
        DataRepository dataRepository = new DataRepository(dataBean.getAll());
        allData.clear();

        for(Iterator itr = dataRepository.getIterator(); itr.hasNext();){
            InfectedData temp = itr.next();

            if(temp.getRegion().getStatus().equals(status)){
                allData.add(temp);
            }
        }

        //filterByDate(new DataRepository(allData));
    }

}
