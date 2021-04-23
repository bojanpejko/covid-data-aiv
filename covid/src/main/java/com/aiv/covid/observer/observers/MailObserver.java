package com.aiv.covid.observer.observers;

import com.aiv.covid.observer.ObserverInterface;
import com.aiv.covid.strategy.*;
import com.aiv.covid.vao.Region;
import com.aiv.covid.vao.ZoneStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Slf4j
public class MailObserver implements ObserverInterface {

    @Valid
    @NotNull(message = "{covid.observer.MailObserver.missing.subject}")
    private Region subject;

    @NotNull(message = "{covid.observer.MailObserver.missing.to}")
    private String to;

    @NotNull(message = "{covid.observer.MailObserver.missing.from}")
    private String from;

    private String mailSubject;

    private void send(String body) {
        try{
            Session session = (Session)new InitialContext().lookup("java:jboss/mail/CustomMail");
            Message message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(mailSubject);
            message.setContent(body, "text/plain");

            Transport.send(message);
        }catch (Exception ex){
            log.info("Failed to send mail!");
            ex.printStackTrace();
        }
    }

    @Override
    public void update() {
        Context strategyContext;

        if(subject.getStatus().equals(ZoneStatus.GREEN)){
            strategyContext = new Context(new GreenZoneStrategy());
        }else if(subject.getStatus().equals(ZoneStatus.YELLOW)){
            strategyContext = new Context(new YellowZoneStrategy());
        }else if(subject.getStatus().equals(ZoneStatus.ORANGE)){
            strategyContext = new Context(new OrangeZoneStrategy());
        }else if(subject.getStatus().equals(ZoneStatus.RED)){
            strategyContext = new Context(new RedZoneStrategy());
        }else{
            strategyContext = new Context(new BlackZoneStrategy());
        }

        send(strategyContext.executeStrategy(subject));
    }
}
