package com.aiv.covid.vao;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;

@NamedQueries({
        @NamedQuery( name = InfectedData.getAll, query = "SELECT d FROM InfectedData d"),
        @NamedQuery( name = InfectedData.getByID, query = "SELECT d FROM InfectedData d WHERE d.uuid = :uuid")
})
@Data
@Entity
@Slf4j
public class InfectedData implements Cloneable {

    public static final String getAll = "InfectedData.getAll";
    public static final String getByID = "InfectedData.getByID";

    @Id
    @Valid
    @Type(type="uuid-char")
    @Column(name = "data_id")
    @NotNull(message = "{covid.vao.InfectedData.id.missing}")
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Region.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "region_id")
    private Region region;

    @Valid
    @NotNull(message = "{covid.vao.InfectedData.day.missing}")
    private Calendar day = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

    private int infected;

    private int hospitalized;

    private int tested;

    public Object clone(){
        try{
            return super.clone();
        }catch (CloneNotSupportedException ex){
            log.info("Failed to clone data!");
            ex.printStackTrace();
        }
        return null;
    }

    public InfectedData deepCopy(){
        InfectedData prototype = (InfectedData) this.clone();
        prototype.setUuid(UUID.randomUUID());

        return prototype;
    }
}
