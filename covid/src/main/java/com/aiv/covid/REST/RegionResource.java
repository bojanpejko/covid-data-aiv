package com.aiv.covid.REST;

import com.aiv.covid.dao.RegionDao;
import com.aiv.covid.dto.region.CreateRegionDTO;
import com.aiv.covid.dto.region.GetRegionDTO;
import com.aiv.covid.dto.region.UpdateRegionDTO;
import com.aiv.covid.vao.Region;
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
@Path("/region")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegionResource {

    @EJB
    private RegionDao regionDao;

    @GET
    public Response getAll(){
        log.info("getAll() => Retrieving all data");
        return Response.ok(regionDao.getAll().stream().map(GetRegionDTO::createDTOFromRegion).collect(Collectors.toList())).build();
    }

    @GET
    @Path("{UUID}")
    public Response getByID(@PathParam("UUID") UUID regionID){
        log.info("getByID() => Searching for data by ID");

        Region region = regionDao.getByID(regionID).orElseThrow(() -> new NotFoundException("Region not found"));

        return Response.ok(
                GetRegionDTO.createDTOFromRegion(region)
        ).build();
    }

    @POST
    public Response create(@Valid CreateRegionDTO createRegionDTO, @Context UriInfo uriInfo){
        log.info("create() => Creating new resource");

        Region region = createRegionDTO.createRegionFromDTO();
        regionDao.save(region);

        return Response.created(uriInfo.getRequestUriBuilder()
                .path(
                        region.getUuid().toString()
                ).build()
        ).build();
    }

    @PUT
    @Path("{UUID}")
    public Response update(@Valid UpdateRegionDTO updateRegionDTO, @PathParam("UUID") UUID uuid){
        log.info("update() => Updating resource");

        Region region = updateRegionDTO.createRegionFormDTO();
        Region regionToBeUpdated = regionDao.getByID(uuid).orElseThrow(() -> new NotFoundException("Region not found"));

        regionToBeUpdated.setName(region.getName());
        regionToBeUpdated.setNumOfInhabitants(region.getNumOfInhabitants());
        regionToBeUpdated.redefineZoneStatus();

        regionDao.save(regionToBeUpdated);

        return Response.noContent().build();
    }

    @DELETE
    @Path("{UUID}")
    public Response delete(@PathParam("UUID") UUID uuid){
        log.info("delete() => Deleting resource");

        regionDao.getByID(uuid).orElseThrow(() -> new NotFoundException("Region not found"));

        regionDao.delete(uuid);

        return Response.noContent().build();
    }
}
