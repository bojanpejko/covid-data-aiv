package com.aiv.covid.dao;

import com.aiv.covid.vao.Region;
import com.aiv.covid.vao.RegionAdministrator;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Local
public interface RegionDao {

    List<Region> getAll();

    Optional<Region> getByID(UUID uuid);

    List<Region> getByAdminID(UUID adminID);

    Optional<RegionAdministrator> getAdminByID(UUID uuid);

    Region save(Region region);

    void saveAdmin(RegionAdministrator admin);

    void delete(UUID uuid);
}
