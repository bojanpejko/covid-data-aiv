package com.aiv.covid.dto.data;

import com.aiv.covid.vao.InfectedData;
import lombok.Data;

import java.util.UUID;

@Data
public class GetDataDTO {

    private UUID regionID;

    private int infected;

    private int hospitalized;

    private int tested;

    public static GetDataDTO createDTOFromData(InfectedData data){
        GetDataDTO dto = new GetDataDTO();

        dto.setRegionID(data.getRegion().getUuid());
        dto.setInfected(data.getInfected());
        dto.setHospitalized(data.getHospitalized());
        dto.setTested(data.getTested());

        return dto;
    }
}
