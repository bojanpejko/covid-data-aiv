package com.aiv.covid.dao;

import com.aiv.covid.vao.RegionAdministrator;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Local
public interface AdminDao {

    List<RegionAdministrator> getAll();

    Optional<RegionAdministrator> getByID(UUID uuid);

    RegionAdministrator save(RegionAdministrator admin);

    void delete(UUID uuid);
}
