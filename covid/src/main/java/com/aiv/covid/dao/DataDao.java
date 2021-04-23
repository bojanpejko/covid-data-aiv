package com.aiv.covid.dao;

import com.aiv.covid.vao.InfectedData;

import javax.ejb.Local;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Local
public interface DataDao {

    List<InfectedData> getAll();

    Optional<InfectedData> getByID(UUID uuid);

    InfectedData save(InfectedData data);

    void delete(UUID uuid);
}
