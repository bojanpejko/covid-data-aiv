package com.aiv.covid.dto.admin;

import com.aiv.covid.vao.RegionAdministrator;
import lombok.Data;

@Data
public class CreateAdminDTO {

    private String name;

    private String surname;

    private String email;

    public RegionAdministrator createAdminFromDTO(){
        RegionAdministrator administrator = new RegionAdministrator();

        administrator.setName(name);
        administrator.setSurname(surname);
        administrator.setEmail(email);

        return administrator;
    }
}
