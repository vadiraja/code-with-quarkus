package com.vadionline.equip.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.neo4j.driver.types.Node;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@NodeEntity
public class Person {

//    public String id;
    @Property(name = "name")
    public String name;
    public String email;
    public String phone;
    public String address;
//    public Date dob;

    @JsonIgnore
    @Relationship(type = "USES", direction = Relationship.Direction.OUTGOING)
    private List<Equipment> equipment;

    public Person() {
        // This is needed for the REST-Easy JSON Binding
    }

//    public Person(String name) {
//        this.name = name;
//    }
//    //We need to add a constructor that takes in all the fields
//    public Person(String id, String name, String email, String phone, String address, String dob) {
//        this.id = id != null ? id : "";
//        this.name = name != null ? name : "";
//        this.email = email != null ? email : "";
//        this.phone = phone != null ? phone : "";
//        this.address = address != null ? address : "";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            this.dob = dob != null ? sdf.parse(dob) : null;
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public Person(String id, String name) {
//        this.id = id;
//    }
//
//
    public Person( String name, String email, String phone, String address) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;

    }

    public Person(String s, String s1, String s2, String s3, Object o) {

    }


    // Create Person from Neo4J Node
    public static Person from(Node node) {
        return new Person(
//                String.valueOf(node.get("id").asLong()),
                node.get("name").isNull() ? null : node.get("name").asString(),
                node.get("email").isNull() ? null : node.get("email").asString(),
                node.get("phone").isNull() ? null : node.get("phone").asString(),
                node.get("address").isNull() ? null : node.get("address").asString()
//                node.get("dob").isNull() ? null : node.get("dob").asString()
        );
    }


}