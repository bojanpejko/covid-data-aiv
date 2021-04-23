package com.aiv.covid.ejb;

import com.aiv.covid.dao.DataDao;
import com.aiv.covid.vao.InfectedData;
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
public class DataBean implements DataDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<InfectedData> getAll() {
       return entityManager.createNamedQuery(InfectedData.getAll, InfectedData.class).getResultList();
    }

    @Override
    public Optional<InfectedData> getByID(UUID uuid) {
        return entityManager.createNamedQuery(InfectedData.getByID, InfectedData.class)
                .setParameter("uuid", uuid)
                .getResultStream()
                .findFirst();
    }

    @Override
    public InfectedData save(InfectedData data) {
        if(data.getUuid() != null){
            return entityManager.merge(data);
        }

        data.setUuid(UUID.randomUUID());
        entityManager.persist(data);

        return data;
    }

    @Override
    public void delete(UUID uuid) {
        InfectedData data = getByID(uuid).orElseThrow();

        entityManager.merge(data);
        entityManager.remove(data);
    }
}
