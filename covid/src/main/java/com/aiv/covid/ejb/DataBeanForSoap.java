package com.aiv.covid.ejb;

import com.aiv.covid.dao.DataDaoForSoap;
import com.aiv.covid.dto.DTOForSoap;
import com.aiv.covid.observer.ObserverInterface;
import com.aiv.covid.observer.ObserverType;
import com.aiv.covid.observer.observers.MailObserver;
import com.aiv.covid.vao.InfectedData;
import com.aiv.covid.vao.Region;
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
import java.util.stream.Collectors;

@Slf4j
@Stateless
@Transactional
@NoArgsConstructor
public class DataBeanForSoap implements DataDaoForSoap {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<DTOForSoap> getAll() {
        List<InfectedData> data = entityManager.createNamedQuery(InfectedData.getAll, InfectedData.class).getResultList();
        return data.stream().map(DTOForSoap::createDTOFromData).collect(Collectors.toList());
    }

    @Override
    public Optional<InfectedData> getByID(String uuid) {
        UUID uuid1 = UUID.fromString(uuid);

        return entityManager.createNamedQuery(InfectedData.getByID, InfectedData.class)
                .setParameter("uuid", uuid1)
                .getResultStream()
                .findFirst();
    }

    private Optional<RegionAdministrator> getAdminByID(UUID uuid){
        return entityManager.createNamedQuery(RegionAdministrator.getByID, RegionAdministrator.class)
                .setParameter("uuid", uuid)
                .getResultStream()
                .findFirst();
    }

    @Override
    public DTOForSoap save(DTOForSoap dto) {
        Region region = dto.createDataFromDTO();
        region.redefineZoneStatus();

        RegionAdministrator administrator = getAdminByID(region.getAdminID()).orElseThrow(() -> new NotFoundException("Admin not found."));

        for(ObserverInterface observer : region.getObservers()){

            if(observer instanceof MailObserver){
                ((MailObserver) observer).setFrom(administrator.getEmail());
                ((MailObserver) observer).setTo(administrator.getEmail());
                ((MailObserver) observer).setMailSubject("Podatke so dodane!");
            }

        }
        region.notifyObservers(ObserverType.ADD);

        entityManager.merge(region);

        return DTOForSoap.createDTOFromData(region.getCalendar().get(region.getCalendar().size() - 1));
    }

    @Override
    public DTOForSoap update(DTOForSoap dto) {
        Region region = dto.createDataFromDTO();
        region.redefineZoneStatus();

        RegionAdministrator administrator = getAdminByID(region.getAdminID()).orElseThrow(() -> new NotFoundException("Admin not found."));

        for(ObserverInterface observer : region.getObservers()){

            if(observer instanceof MailObserver){
                ((MailObserver) observer).setFrom(administrator.getEmail());
                ((MailObserver) observer).setTo(administrator.getEmail());
                ((MailObserver) observer).setMailSubject("Sprememba podatkov!");
            }

        }
        region.notifyObservers(ObserverType.ADD);

        entityManager.merge(region);

        return DTOForSoap.createDTOFromData(region.getCalendar().get(region.getCalendar().size() - 1));
    }

    @Override
    public void delete(String uuid) {
        InfectedData data = getByID(uuid).orElseThrow();

        entityManager.merge(data);
        entityManager.remove(data);

        data.getRegion().redefineZoneStatus();
    }


}
