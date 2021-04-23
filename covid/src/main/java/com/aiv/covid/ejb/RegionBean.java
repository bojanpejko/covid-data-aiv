package com.aiv.covid.ejb;

import com.aiv.covid.dao.RegionDao;
import com.aiv.covid.vao.Region;
import com.aiv.covid.vao.RegionAdministrator;
import com.aiv.covid.vao.ZoneStatus;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Stateless
@Transactional
@NoArgsConstructor
public class RegionBean implements RegionDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Region> getAll() {
        return entityManager.createNamedQuery(Region.getAll, Region.class).getResultList();
    }

    @Override
    public Optional<Region> getByID(UUID uuid) {
       return entityManager.createNamedQuery(Region.getByID, Region.class)
               .setParameter("uuid", uuid)
               .getResultStream()
               .findFirst();
    }

    @Override
    public List<Region> getByAdminID(UUID adminID) {
        return entityManager.createNamedQuery(Region.getByAdminID, Region.class)
                .setParameter("uuid", adminID)
                .getResultList();
    }

    @Override
    public Optional<RegionAdministrator> getAdminByID(UUID uuid) {
        return entityManager.createNamedQuery(RegionAdministrator.getByID, RegionAdministrator.class)
                .setParameter("uuid", uuid)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Region save(Region region) {
        if (region.getUuid() != null) {
            return entityManager.merge(region);
        }

        region.setUuid(UUID.randomUUID());
        region.setStatus(ZoneStatus.GREEN);

        entityManager.persist(region);

        return region;
    }

    @Override
    public void delete(UUID uuid) {
        Region region = getByID(uuid).orElseThrow();

        region.getCalendar().clear();

        entityManager.merge(region);
        entityManager.remove(region);
    }

    @Override
    public void saveAdmin(RegionAdministrator admin){
        admin.setUuid(UUID.fromString("0089b5d8-7280-4c4c-a8d3-c5fd71da4346"));

        if (admin.getUuid() != null)
            entityManager.merge(admin);
        else
            entityManager.persist(admin);


    }

}
