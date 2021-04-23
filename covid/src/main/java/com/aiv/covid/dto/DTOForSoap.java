package com.aiv.covid.dto;

import com.aiv.covid.vao.InfectedData;
import com.aiv.covid.vao.Region;
import com.aiv.covid.vao.ZoneStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

@Data
public class DTOForSoap {

    private String id;

    private int infected;

    private int hospitalized;

    private int tested;

    private String regionID;

    private String name;

    private int numOfInhabitants;

    private ZoneStatus status;

    private String adminID;

    public Region createDataFromDTO(){
        Region region = new Region();

        region.setUuid(UUID.fromString(regionID));
        region.setName(name);
        region.setAdminID(UUID.fromString(adminID));
        region.setStatus(status);
        region.setNumOfInhabitants(numOfInhabitants);
        region.setCalendar(new ArrayList<>());

        InfectedData data = new InfectedData();

        data.setUuid(UUID.fromString(id));
        data.setDay(Calendar.getInstance());
        data.setRegion(region);
        data.setInfected(infected);
        data.setHospitalized(hospitalized);
        data.setTested(tested);

        region.addDailyData(data);
        data.setRegion(region);
        return region;
    }

    public static DTOForSoap createDTOFromData(InfectedData data){
        DTOForSoap dto = new DTOForSoap();

        dto.setId(data.getUuid().toString());
        dto.setInfected(data.getInfected());
        dto.setHospitalized(data.getHospitalized());
        dto.setTested(data.getTested());
        dto.setName(data.getRegion().getName());
        dto.setAdminID(data.getRegion().getAdminID().toString());
        dto.setStatus(data.getRegion().getStatus());
        dto.setRegionID(data.getRegion().getUuid().toString());
        dto.setNumOfInhabitants(data.getRegion().getNumOfInhabitants());

        return dto;
    }

}
