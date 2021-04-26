package com.aiv.covid.dto.region;

import com.aiv.covid.vao.Region;
import lombok.Data;

@Data
public class UpdateRegionDTO {

    private String name;

    private int number;

    public Region createRegionFormDTO(){
        Region region = new Region();

        region.setName(name);
        region.setNumOfInhabitants(number);

        return region;
    }
}
