package com.aiv.covid.dto.data;

import com.aiv.covid.vao.InfectedData;
import lombok.Data;

import java.util.Calendar;

@Data
public class UpdateDataDTO {

    private int infected;

    private int hospitalized;

    private int tested;

    public InfectedData createDataFromDTO(){
        InfectedData data = new InfectedData();

        data.setTested(tested);
        data.setInfected(infected);
        data.setHospitalized(hospitalized);
        data.setDay(Calendar.getInstance());

        return data;
    }
}
