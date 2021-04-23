package com.aiv.soap;

import com.aiv.soap.gen.DataSoap;
import com.aiv.soap.gen.DataSoapService;
import com.aiv.soap.gen.DtoForSoap;
import com.aiv.soap.gen.ZoneStatus;

import java.util.List;
import java.util.UUID;

public class DataSoapClient {

    public static void main(String[] args){
        DataSoap soap = new DataSoapService().getDataSoapPort();

        DtoForSoap dto = new DtoForSoap();

        dto.setId(UUID.randomUUID().toString());
        dto.setInfected(300);
        dto.setHospitalized(15);
        dto.setTested(3000);
        dto.setStatus(ZoneStatus.GREEN);
        dto.setName("Maribor");
        dto.setAdminID("0089b5d8-7280-4c4c-a8d3-c5fd71da4346");
        dto.setRegionID("b82fe9f9-7676-4532-8ef3-a047b411af71");
        dto.setNumOfInhabitants(100000);

        dto = soap.create(dto);

        dto.setInfected(600);
        soap.update(dto, dto.getId());

        List<DtoForSoap> dtos = soap.getAll();
        for(DtoForSoap temp : dtos){
            System.out.println(temp.toString());
        }

        soap.delete(dto.getId());
    }
}
