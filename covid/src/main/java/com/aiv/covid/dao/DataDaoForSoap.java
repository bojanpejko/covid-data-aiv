package com.aiv.covid.dao;

import com.aiv.covid.dto.DTOForSoap;
import com.aiv.covid.vao.InfectedData;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface DataDaoForSoap {

    List<DTOForSoap> getAll();

    Optional<InfectedData> getByID(String uuid);

    DTOForSoap save(DTOForSoap dto);

    DTOForSoap update(DTOForSoap dto);

    void delete(String uuid);
}
