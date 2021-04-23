package com.aiv.covid.vao;

import com.aiv.covid.observer.ObserverInterface;
import com.aiv.covid.observer.ObserverType;
import com.aiv.covid.observer.observers.LogObserver;
import com.aiv.covid.observer.observers.MailObserver;
import com.aiv.covid.observer.observers.NoDataObserver;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@NamedQueries({
        @NamedQuery(name = Region.getAll, query = "SELECT r FROM Region r"),
        @NamedQuery(name = Region.getByID, query = "SELECT r FROM Region r WHERE r.uuid = :uuid"),
        @NamedQuery(name = Region.getByAdminID, query = "SELECT r FROM Region r WHERE r.adminID = :uuid")
})
@Getter
@Setter
@Entity
@EqualsAndHashCode
public class Region implements Subject{

    public static final String getAll = "Region.getAll";
    public static final String getByID = "Region.getByID";
    public static final String getByAdminID = "Region.getByAdminID";

    @Id
    @Valid
    @Type(type="uuid-char")
    @Column(name = "region_id")
    private UUID uuid;

    @NotNull(message = "{covid.vao.Region.name.missing}")
    private String name;

    @NotNull(message = "{covid.vao.Region.numOfInhabitants.missing}")
    private int numOfInhabitants;

    @NotNull(message = "{covid.vao.Region.status.missing}")
    @Enumerated(EnumType.STRING)
    private ZoneStatus status;

    @Valid
    @Type(type="uuid-char")
    @Column(name = "admin_id")
    @NotNull(message = "{covid.vao.Region.administrator.missing}")
    private UUID adminID;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "region")
    private List<InfectedData> calendar;

    @Transient
    List<ObserverInterface> observers = new ArrayList<>();

    public Region(){
        LogObserver logObserver = new LogObserver();
        logObserver.setSubject(this);

        MailObserver mailObserver = new MailObserver();
        mailObserver.setSubject(this);

        NoDataObserver noDataObserver = new NoDataObserver();
        noDataObserver.setSubject(this);

        attachObservers(logObserver, mailObserver, noDataObserver);
    }

    public void addDailyData(InfectedData data) {
        if (data == null) {
            return;
        }
        if (this.calendar == null) {
            this.calendar = new ArrayList<>();
        }
        this.calendar.add(data);
    }

    public void redefineZoneStatus(){
        Calendar today = Calendar.getInstance();
        int lastWeekInfected = 0;
        int lastWeekTested = 0;

        if(calendar.isEmpty()){
            return;
        }

        for(InfectedData data : calendar){
            if(data.getDay().getWeekYear() == today.getWeekYear()){
                lastWeekInfected += data.getInfected();
                lastWeekTested += data.getTested();
            }
        }

        double percentage = ((double)lastWeekInfected / (double)lastWeekTested) * 100;

        if(percentage <= 10.0){
            setStatus(ZoneStatus.GREEN);
        }else if(percentage <= 20.0){
            setStatus(ZoneStatus.YELLOW);
        }else if(percentage <= 30.0){
            setStatus(ZoneStatus.ORANGE);
        }else if(percentage <= 40.0){
            setStatus(ZoneStatus.RED);
        }else{
            setStatus(ZoneStatus.BLACK);
        }
    }

    @Override
    public String toString() {
        return "Region{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", numOfInhabitants=" + numOfInhabitants +
                ", adminID=" + adminID +
                ", status=" + status +
                '}';
    }

    @Override
    public void attachObservers(ObserverInterface... observers) { this.observers.addAll(Arrays.asList(observers)); }

    @Override
    public void notifyObservers(ObserverType type) {

        if(type == ObserverType.NODATA){
            for(ObserverInterface observer: observers){
                if(observer instanceof NoDataObserver){
                    observer.update();
                }
            }
        }else{
            for(ObserverInterface observer: observers){
                if(!(observer instanceof NoDataObserver)){
                    observer.update();
                }
            }
        }
    }
}
