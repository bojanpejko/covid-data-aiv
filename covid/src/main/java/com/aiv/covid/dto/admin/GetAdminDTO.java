package com.aiv.covid.dto.admin;

import com.aiv.covid.vao.RegionAdministrator;
import lombok.Data;

import java.util.UUID;

@Data
public class GetAdminDTO {

    private UUID uuid;

    private String name;

    private String surname;

    private String email;

    public static GetAdminDTO createDTOFromAdmin(RegionAdministrator admin){
        GetAdminDTO adminDTO = new GetAdminDTO();

        adminDTO.setUuid(admin.getUuid());
        adminDTO.setName(admin.getName());
        adminDTO.setSurname(admin.getSurname());
        adminDTO.setEmail(admin.getEmail());

        return adminDTO;
    }
}
