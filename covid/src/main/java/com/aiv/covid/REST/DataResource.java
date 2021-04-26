package com.aiv.covid.REST;

import com.aiv.covid.dao.DataDao;
import com.aiv.covid.dao.RegionDao;
import com.aiv.covid.dto.data.CreateDataDTO;
import com.aiv.covid.dto.data.GetDataDTO;
import com.aiv.covid.dto.data.UpdateDataDTO;
import com.aiv.covid.observer.ObserverInterface;
import com.aiv.covid.observer.ObserverType;
import com.aiv.covid.observer.observers.MailObserver;
import com.aiv.covid.vao.InfectedData;
import com.aiv.covid.vao.Region;
import com.aiv.covid.vao.RegionAdministrator;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Path("/data")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DataResource {

    @EJB
    private DataDao dataDao;

    @EJB
    private RegionDao regionDao;

    @GET
    public Response getAll(){
        log.info("getAll() => Retrieving all data");
        return Response.ok(dataDao.getAll().stream().map(GetDataDTO::createDTOFromData).collect(Collectors.toList())).build();
    }

    @GET
    @Path("{UUID}")
    public Response getByID(@PathParam("UUID") UUID infectedDataID){
        log.info("getByID() => Searching for data by ID");

        InfectedData data = dataDao.getByID(infectedDataID).orElseThrow(() -> new NotFoundException("Data not found"));

        return Response.ok(
                GetDataDTO.createDTOFromData(data)
        ).build();
    }

    @POST
    public Response create(@Valid CreateDataDTO createDataDTO, @Context UriInfo uriInfo){
        log.info("create() => Creating new resource");

        InfectedData data = createDataDTO.createDataFromDTO();
        Region region = regionDao.getByID(createDataDTO.getRegionID()).orElseThrow(() -> new NotFoundException("Region not found"));

        data.setRegion(region);
        region.addDailyData(data);
        data.getRegion().redefineZoneStatus();

        RegionAdministrator regionAdministrator = regionDao.getAdminByID(region.getAdminID()).orElseThrow(() -> new NotFoundException("Admin not found"));

        for(ObserverInterface observer : region.getObservers()){

            if(observer instanceof MailObserver){
                ((MailObserver) observer).setFrom(regionAdministrator.getEmail());
                ((MailObserver) observer).setTo(regionAdministrator.getEmail());
                ((MailObserver) observer).setMailSubject("Nove podatke!");
            }

        }
        region.notifyObservers(ObserverType.ADD);

        regionDao.save(region);

        return Response.created(uriInfo.getRequestUriBuilder()
            .path(
                    data.getUuid().toString()
            ).build()
        ).build();
    }

    @PUT
    @Path("{UUID}")
    public Response update(@Valid UpdateDataDTO updateDTO, @PathParam("UUID") UUID uuid){
        log.info("update() => Updating resource");

        InfectedData data = updateDTO.createDataFromDTO();

        InfectedData dataToBeUpdated = dataDao.getByID(uuid).orElseThrow(() -> new NotFoundException("Data not found"));
        dataToBeUpdated.setInfected(data.getInfected());
        dataToBeUpdated.setHospitalized(data.getHospitalized());
        dataToBeUpdated.setTested(data.getTested());

        Region region = regionDao.getByID(dataToBeUpdated.getRegion().getUuid()).orElseThrow(() -> new NotFoundException("Region not found"));

        RegionAdministrator regionAdministrator = regionDao.getAdminByID(region.getAdminID()).orElseThrow(() -> new NotFoundException("Admin not found"));

        for(ObserverInterface observer : region.getObservers()){

            if(observer instanceof MailObserver){
                ((MailObserver) observer).setFrom(regionAdministrator.getEmail());
                ((MailObserver) observer).setTo(regionAdministrator.getEmail());
                ((MailObserver) observer).setMailSubject("Sprememba podatkov!");
            }

        }
        dataDao.save(dataToBeUpdated);

        region.redefineZoneStatus();
        regionDao.save(region);

        region.notifyObservers(ObserverType.ADD);

        return Response.noContent().build();
    }

    @DELETE
    @Path("{UUID}")
    public Response delete(@PathParam("UUID") UUID uuid){
        log.info("delete() => Deleting resource");

        InfectedData data = dataDao.getByID(uuid).orElseThrow(() -> new NotFoundException("Data not found"));
        UUID regionID = data.getRegion().getUuid();

        dataDao.delete(uuid);

        Region region = regionDao.getByID(regionID).orElseThrow(() -> new NotFoundException("Region not found"));
        region.redefineZoneStatus();


        if(region.getCalendar().isEmpty())
            region.notifyObservers(ObserverType.NODATA);
        else{
            RegionAdministrator regionAdministrator = regionDao.getAdminByID(region.getAdminID()).orElseThrow(() -> new NotFoundException("Admin not found"));

            for(ObserverInterface observer : region.getObservers()){

                if(observer instanceof MailObserver){
                    ((MailObserver) observer).setFrom(regionAdministrator.getEmail());
                    ((MailObserver) observer).setTo(regionAdministrator.getEmail());
                    ((MailObserver) observer).setMailSubject("Izbris podatkov!");
                }

            }
            region.notifyObservers(ObserverType.ADD);
        }

        return Response.noContent().build();
    }
}
