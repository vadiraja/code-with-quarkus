package com.vadionline.equip.repo;

import com.vadionline.equip.entities.MaintenanceLog;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.context.ThreadContext;
import org.neo4j.driver.Driver;
import org.neo4j.ogm.session.SessionFactory;

import java.util.Map;

@Path("/log")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MaintenanceLogResource {

    @Inject
    SessionFactory sessionFactory;
    @Inject
    Driver driver;

    @Inject
    ThreadContext threadContext;

    public MaintenanceLogResource() {
    }

    //Get maintenance by oid
    @GET
    @Path("/{oid}")
    public Response getMaintenanceLogByOid(Long oid) {
        //Get Maintenance log
        MaintenanceLog maintenanceLog = sessionFactory.openSession().load(MaintenanceLog.class, oid, 1);
        return Response.ok(maintenanceLog).build();
    }



}
