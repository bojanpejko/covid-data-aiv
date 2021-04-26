package com.aiv.covid.vao;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@NamedQueries({
        @NamedQuery(name = RegionAdministrator.getAll, query = "SELECT a FROM RegionAdministrator a"),
        @NamedQuery(name = RegionAdministrator.getByID, query = "SELECT a FROM RegionAdministrator a WHERE a.uuid = :uuid")
})
public class RegionAdministrator {

    public static final String getAll = "RegionAdministrator.getAll";
    public static final String getByID = "RegionAdministrator.getByID";

    private static RegionAdministrator instance = null;

    public static RegionAdministrator getInstance() {
        if(instance == null){
            instance = new RegionAdministrator();
        }
        return instance;
    }

    public static void setInstance(RegionAdministrator instance) {
        RegionAdministrator.instance = instance;
    }

    @Id
    @Valid
    @Type(type="uuid-char")
    @Column(name = "admin_id")
    @NotNull(message = "{covid.vao.RegionAdministrator.uuid.missing}")
    private UUID uuid;

    @Column(name = "admin_name")
    @NotNull(message = "{covid.vao.RegionAdministrator.name.missing}")
    private String name;

    @NotNull(message = "{covid.vao.RegionAdministrator.surname.missing}")
    private String surname;

    @Email
    @NotNull(message = "{covid.vao.RegionAdministrator.email.missing}")
    private String email;

}
