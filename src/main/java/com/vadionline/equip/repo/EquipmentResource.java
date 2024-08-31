package com.vadionline.equip.repo;

import com.vadionline.equip.entities.Equipment;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import org.eclipse.microprofile.context.ThreadContext;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Values;
import org.neo4j.driver.async.AsyncSession;
import org.neo4j.driver.async.ResultCursor;
import com.vadionline.equip.services.EquipmentService;

@Path("/equipment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EquipmentResource {

    @Inject
    Driver driver;

    @Inject
    ThreadContext threadContext;

    //Inject EquipmentService
    @Inject
    EquipmentService equipmentService;


//    @GET
//    public CompletionStage<Response> get() {
//        AsyncSession session = driver.session(AsyncSession.class);
//        CompletionStage<List<Equipment>> cs = session.executeReadAsync(tx ->
//            tx
//                .runAsync("MATCH (e:Equipment) RETURN e")
//                .thenCompose(cursor ->
//                    cursor.listAsync(record ->
//                        Equipment.from(record.get("e").asNode())
//                    )
//                )
//        );
//        return threadContext
//            .withContextCapture(cs)
//            .thenCompose(equipments ->
//                session.closeAsync().thenApply(signal -> equipments)
//            )
//            .thenApply(Response::ok)
//            .thenApply(Response.ResponseBuilder::build);
//    }

    //Get all equipment completion stage using services
    @GET
    public Response getAllEquipment() {
        List<Equipment> equipments = List.of(equipmentService.getAllEquipment());
        return Response.ok(equipments).build();
    }


    @GET
    @Path("/{id}")
    public CompletionStage<Response> getSingle(String id) {
        System.out.println("ID: " + id);
        AsyncSession session = driver.session(AsyncSession.class);
        CompletionStage<Equipment> cs = session.executeReadAsync(tx -> {

                    String query = "MATCH (e:Equipment) WHERE e.id = $id RETURN e";
            System.out.println("Executing query: " + query + " with id: " + id);
                    return tx
                            .runAsync(query, Values.parameters("id", Integer.parseInt(id)))
                            .thenCompose(ResultCursor::singleAsync)
                            .thenApply(record -> Equipment.from(record.get("e").asNode()));
                }
        );


        return threadContext
            .withContextCapture(cs)
            .thenCompose(equipment ->
                session.closeAsync().thenApply(signal -> equipment)
            )
            .thenApply(equipment -> Response.ok(equipment).build());

    }

    @POST
    public CompletionStage<Response> create(Equipment equipment) {
        AsyncSession session = driver.session(AsyncSession.class);
        Map<String, Object> parameters = new HashMap<>();
        if (equipment.name != null) parameters.put("name", equipment.name);
        if (equipment.manufacturer != null) parameters.put(
            "manufacturer",
            equipment.manufacturer
        );
        if (equipment.model != null) parameters.put("model", equipment.model);
        if (equipment.serialNumber != null) parameters.put(
            "serialNumber",
            equipment.serialNumber
        );
        if (equipment.productionDate != null) parameters.put(
            "productionDate",
            equipment.productionDate.toString()
        );
        if (equipment.purchaseDate != null) parameters.put(
            "purchaseDate",
            equipment.purchaseDate.toString()
        );
        // Add id field
        parameters.put("id", equipment.id);


        System.out.println("Equipment: " + equipment);
        System.out.println("Parameters: " + parameters);

        String finalQuery = getString(equipment);
        System.out.println("Query: " + finalQuery);
        CompletionStage<Equipment> cs = session.executeWriteAsync(tx ->
            tx
                .runAsync(finalQuery, parameters)
                .thenCompose(ResultCursor::singleAsync)
                .thenApply(record -> Equipment.from(record.get("e").asNode()))
        );

        return threadContext
            .withContextCapture(cs)
            .thenCompose(equipment1 ->
                session.closeAsync().thenApply(signal -> equipment1)
            )
            .thenApply(equipment1 ->
                jakarta.ws.rs.core.Response.created(
                    URI.create("/equipment/" + equipment1.id)
                )
                    .entity(equipment1)
                    .build()
            );
    }

    private static String getString(Equipment equipment) {
        String query = "CREATE (e:Equipment {";
        if (equipment.name != null) query += "name: $name, ";
        if (equipment.manufacturer != null) query +=
        "manufacturer: $manufacturer, ";
        if (equipment.model != null) query += "model: $model, ";
        if (equipment.serialNumber != null) query +=
        "serialNumber: $serialNumber, ";
        if (equipment.productionDate != null) query +=
        "productionDate: $productionDate, ";
        if (equipment.purchaseDate != null) query +=
        "purchaseDate: $purchaseDate, ";
        query = query.substring(0, query.length() - 2); // remove the last comma and space
        query += "}) RETURN e";

        String finalQuery = query;
        return finalQuery;
    }

    //Write common operations methods on the Equipment entity with Neo4j as the backend
    //Create a method to update an Equipment entity
    //Create a method to delete an Equipment entity
    //Create a method to get all Equipment entities
    //Create a method to get a single Equipment entity
    //Create a method to create an Equipment entity

    @PUT
    public CompletionStage<Response> update(Equipment equipment) {
        AsyncSession session = driver.session(AsyncSession.class);
        CompletionStage<Equipment> cs = session.executeWriteAsync(tx ->
            tx
                .runAsync(
                    "MATCH (e:Equipment) WHERE id(e) = $id SET e.name = $name RETURN e",
                    Map.of("id", equipment.id, "name", equipment.name)
                )
                .thenCompose(ResultCursor::singleAsync)
                .thenApply(record -> Equipment.from(record.get("e").asNode()))
        );

        return threadContext
            .withContextCapture(cs)
            .thenCompose(equipment1 ->
                session.closeAsync().thenApply(signal -> equipment1)
            )
            .thenApply(equipment1 -> Response.ok(equipment1).build());
    }

    @DELETE
    @Path("/{id}")
    public CompletionStage<Response> delete(String id) {
        AsyncSession session = driver.session(AsyncSession.class);
        CompletionStage<Equipment> cs = session.executeWriteAsync(tx ->
            tx
                .runAsync(
                    "MATCH (e:Equipment) WHERE id(e) = $id DELETE e",
                    Map.of("id", id)
                )
                .thenApply(result -> new Equipment(id, ""))
        );

        return threadContext
            .withContextCapture(cs)
            .thenCompose(equipment1 ->
                session.closeAsync().thenApply(signal -> equipment1)
            )
            .thenApply(equipment1 -> Response.ok(equipment1).build());
    }

    @GET
    @Path("/all")
    public CompletionStage<Response> getAll(
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("size") @DefaultValue("10") int size
    ) {
        int skip = page * size;
        AsyncSession session = driver.session(AsyncSession.class);
        CompletionStage<List<Equipment>> cs = session.executeReadAsync(tx ->
            tx
                .runAsync(
                    "MATCH (e:Equipment) RETURN e SKIP $skip LIMIT $limit",
                    Map.of("skip", skip, "limit", size)
                )
                .thenCompose(cursor ->
                    cursor.listAsync(record ->
                        Equipment.from(record.get("e").asNode())
                    )
                )
        );
        return threadContext
            .withContextCapture(cs)
            .thenCompose(equipments ->
                session.closeAsync().thenApply(signal -> equipments)
            )
            .thenApply(Response::ok)
            .thenApply(Response.ResponseBuilder::build);
    }
}
