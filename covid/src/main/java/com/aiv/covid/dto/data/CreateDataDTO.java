package com.aiv.covid.dto.data;

import com.aiv.covid.vao.InfectedData;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateDataDTO {

    private UUID regionID;

    private int infected;

    private int hospitalized;

    private int tested;

    public InfectedData createDataFromDTO(){
        InfectedData data = new InfectedData();

        data.setUuid(UUID.randomUUID());
        data.setTested(tested);
        data.setInfected(infected);
        data.setHospitalized(hospitalized);

        return data;
    }
}
