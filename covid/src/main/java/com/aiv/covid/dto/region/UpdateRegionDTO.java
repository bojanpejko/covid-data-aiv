package com.aiv.covid.dto.region;

import com.aiv.covid.vao.InfectedData;
import com.aiv.covid.vao.Region;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UpdateRegionDTO {

    private String name;

    private int number;

    private UUID adminID;

    private List<InfectedData> calendar;

    public Region createRegionFormDTO(){
        Region region = new Region();

        region.setName(name);
        region.setNumOfInhabitants(number);
        region.setAdminID(adminID);
        region.setCalendar(calendar);

        return region;
    }
}
