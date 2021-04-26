package com.aiv.covid.ejb;

import com.aiv.covid.dao.AdminDao;
import com.aiv.covid.vao.RegionAdministrator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Stateless
@Transactional
@NoArgsConstructor
public class AdminBean implements AdminDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<RegionAdministrator> getAll() {
        return entityManager.createNamedQuery(RegionAdministrator.getAll, RegionAdministrator.class).getResultList();
    }

    @Override
    public Optional<RegionAdministrator> getByID(UUID uuid) {
        return entityManager.createNamedQuery(RegionAdministrator.getByID, RegionAdministrator.class)
                .setParameter("uuid", uuid)
                .getResultStream()
                .findFirst();
    }

    @Override
    public RegionAdministrator save(RegionAdministrator admin) {
        if(admin.getUuid() != null)
            return entityManager.merge(admin);

        admin.setUuid(UUID.randomUUID());
        entityManager.persist(admin);

        return admin;
    }

    @Override
    public void delete(UUID uuid) {
        RegionAdministrator admin  = getByID(uuid).orElseThrow(() -> new NotFoundException("Admin not found."));

        entityManager.merge(admin);
        entityManager.remove(admin);
    }
}
