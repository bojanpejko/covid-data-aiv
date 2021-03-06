package com.aiv.covid.dto.region;

import com.aiv.covid.vao.Region;
import com.aiv.covid.vao.ZoneStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateRegionDTO {

    private String name;

    private int number;

    private UUID adminID;

    public Region createRegionFromDTO(){
        Region region = new Region();

        region.setName(name);
        region.setNumOfInhabitants(number);
        region.setAdminID(adminID);
        region.setStatus(ZoneStatus.GREEN);

        return region;
    }
}
