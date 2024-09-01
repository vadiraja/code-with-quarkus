package com.vadionline.equip.repo;

import com.vadionline.equip.entities.Person;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.context.ThreadContext;
import org.neo4j.driver.Driver;
import org.neo4j.driver.async.AsyncSession;
import org.neo4j.driver.async.ResultCursor;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.Map;


@Path("/person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    //Inject the driver
    @Inject
    Driver driver;

    //Inject the thread context
    @Inject
    ThreadContext threadContext;

    //Get all persons completion stage
//    @GET
//    public CompletionStage<Response> get() {
//        AsyncSession session = driver.session(AsyncSession.class);
//        CompletionStage<List<Person>> cs = session.executeReadAsync(tx ->
//                tx.runAsync("MATCH (p:Person) RETURN p")
//                        .thenCompose(cursor -> cursor.listAsync(record ->
//                                Person.from(record.get("p").asNode()))));
//        return threadContext.withContextCapture(cs)
//                .thenCompose(persons ->
//                    session.closeAsync().thenApply(signal -> persons))
//                    .thenApply(Response::ok).thenApply(Response.ResponseBuilder::build);
//    }
//
//    //Get single person completion stage
//    @GET
//    @Path("/{id}")
//    public CompletionStage<Response> getSingle(String id) {
//        System.out.println("ID: " + id);
//        AsyncSession session = driver.session(AsyncSession.class);
//        CompletionStage<Person> cs = session.executeReadAsync(tx ->
//                tx.runAsync("MATCH (p:Person) WHERE id(p) = $id RETURN p",
//                        Map.of("id", Integer.parseInt(id)))
//                        .thenCompose(ResultCursor::singleAsync)
//                        .thenApply(record -> Person.from(record.get("p").asNode())));
//
//        return threadContext.withContextCapture(cs)
//                .thenCompose(person ->
//                    session.closeAsync().thenApply(signal -> person))
//                    .thenApply(person -> Response.ok(person).build());
//    }
//
//    //Create a person completion stage
//    @POST
//    public CompletionStage<Response> create(Person person) {
//        AsyncSession session = driver.session(AsyncSession.class);
//        //write a map of parameters to the database after checking Person entity fields for null
//        Map<String, Object> parameters = new HashMap<>();
//        if (person.name != null) parameters.put("name", person.name);
//        if (person.email != null) parameters.put("email", person.email);
//        if (person.phone != null) parameters.put("phone", person.phone);
//        if (person.address != null) parameters.put("address", person.address);
//        if (person.dob != null) parameters.put("dob", person.dob);
//
//        String finalQuery = getString(person);
//        System.out.println("Query: " + finalQuery);
//
//        CompletionStage<Person> cs = session.executeWriteAsync(tx ->
//                tx.runAsync(finalQuery, parameters)
//                        .thenCompose(ResultCursor::singleAsync)
//                        .thenApply(record -> Person.from(record.get("p").asNode())));
//
//            return threadContext.withContextCapture(cs)
//                    .thenCompose(person1 ->
//                        session.closeAsync().thenApply(signal -> person))
//                        .thenApply(person1 -> Response.created(URI.create("/person/" + person.id)).entity(person).build());
//    }
//
//    //fix write a getString method that takes in a Person entity and returns a query string
//    private static String getString(Person person) {
//        String query = "CREATE (p:Person {";
//        if (person.name != null) query += "name: $name, ";
//        if (person.email != null) query += "email: $email, ";
//        if (person.phone != null) query += "phone: $phone, ";
//        if (person.address != null) query += "address: $address, ";
//        if (person.dob != null) query += "dob: $dob, ";
//        query = query.substring(0, query.length() - 2);
//        query += "}) RETURN p";
//        return query;
//    }
//
//
//    //Update a person completion stage
//    @PUT
//    @Path("/{id}")
//    public CompletionStage<Response> update(String id, Person person) {
//        AsyncSession session = driver.session(AsyncSession.class);
//        CompletionStage<Person> cs = session.executeWriteAsync(tx ->
//                tx.runAsync("MATCH (p:Person) WHERE id(p) = $id SET p = $person RETURN p",
//                        Map.of("id", Integer.parseInt(id), "person", person))
//                        .thenCompose(ResultCursor::singleAsync)
//                        .thenApply(record -> Person.from(record.get("p").asNode())));
//
//        return threadContext.withContextCapture(cs)
//                .thenCompose(updatedPerson ->
//                    session.closeAsync().thenApply(signal -> updatedPerson))
//                    .thenApply(updatedPerson -> Response.ok(updatedPerson).build());
//    }
//
//    //Delete a person completion stage
//    @DELETE
//    @Path("/{id}")
//    public CompletionStage<Response> delete(String id) {
//        AsyncSession session = driver.session(AsyncSession.class);
//        CompletionStage<Person> cs = session.executeWriteAsync(tx ->
//                tx.runAsync("MATCH (p:Person) WHERE id(p) = $id DELETE p",
//                        Map.of("id", Integer.parseInt(id)))
//                        .thenApply(result -> new Person(id, "")));
//
//        return threadContext.withContextCapture(cs)
//                .thenCompose(signal ->
//                    session.closeAsync().thenApply(v -> signal))
//                    .thenApply(signal -> Response.noContent().build());
//    }

    // Get the Equipment associated with a Person
    @GET
    @Path("/{id}/equipment")
    public CompletionStage<Response> getEquipment(@PathParam("id") String id) {
        AsyncSession session = driver.session(AsyncSession.class);
        CompletionStage<List<Person>> cs = session.executeReadAsync(tx ->
                tx.runAsync("MATCH (p:Person {id:$id})-[:USES]->(e:Equipment) RETURN e",
                        Map.of("id", Integer.parseInt(id)))
                        .thenCompose(cursor -> cursor.listAsync(record ->
                                Person.from(record.get("e").asNode()))));
        return threadContext.withContextCapture(cs)
                .thenCompose(persons ->
                    session.closeAsync().thenApply(signal -> persons))
                    .thenApply(Response::ok).thenApply(Response.ResponseBuilder::build);
    }








}
