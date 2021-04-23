package com.aiv.covid.SOAP;

import com.aiv.covid.dao.DataDaoForSoap;
import com.aiv.covid.dto.DTOForSoap;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.NotFoundException;
import java.util.List;

@WebService
public class DataSoap {

    @EJB
    public DataDaoForSoap dataDaoForSoap;

    public List<DTOForSoap> getAll(){ return dataDaoForSoap.getAll(); }

    public String getByID(String uuid){
        return dataDaoForSoap.getByID(uuid).orElseThrow(() -> new NotFoundException("Data not found.")).toString();
    }

    public DTOForSoap create(DTOForSoap dto){ return dataDaoForSoap.save(dto); }

    public DTOForSoap update(DTOForSoap dto, String dataID){
        dataDaoForSoap.getByID(dataID).orElseThrow(() -> new NotFoundException("Data not found."));

        return dataDaoForSoap.update(dto);
    }

    public void delete(String uuid){ dataDaoForSoap.delete(uuid); }
}
