package com.aiv.covid.REST;

import com.aiv.covid.dao.AdminDao;
import com.aiv.covid.dto.admin.CreateAdminDTO;
import com.aiv.covid.dto.admin.GetAdminDTO;
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
@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminResource {

    @EJB
    private AdminDao adminDao;

    @GET
    public Response getAll(){
        log.info("getAll() => Retrieving all data");
        return Response.ok(adminDao.getAll().stream().map(GetAdminDTO::createDTOFromAdmin).collect(Collectors.toList())).build();
    }

    @POST
    public Response create(@Valid CreateAdminDTO dto, @Context UriInfo uriInfo){
        log.info("create() => Creating new resource");

        RegionAdministrator administrator = dto.createAdminFromDTO();

        adminDao.save(administrator);

        return Response.created(uriInfo.getRequestUriBuilder()
                .path(
                        administrator.getUuid().toString()
                ).build()
        ).build();
    }

    @DELETE
    @Path("{UUID}")
    public Response delete(@PathParam("UUID") UUID adminID){
        log.info("delete() => Deleting resource");

       adminDao.delete(adminID);
       return Response.noContent().build();
    }
}
