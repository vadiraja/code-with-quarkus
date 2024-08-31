package com.vadionline.equip.entities;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import jakarta.json.bind.annotation.JsonbTypeSerializer;
import org.neo4j.driver.types.Node;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Equipment {

    //add an id field

    //map to id attribute in Equipment class
    public Long id;
    public String name;
//    public String manufacturer;
//    public String model;
//    public String serialNumber;
//    @Property(name = "productionDate")
//    public Date productionDate;
//    public Date purchaseDate;
//    public String specification;
//    public String machineType;
//    //add an uri field for image
//    public String imageUri;

//    @Relationship(type = "HAS_LOG", direction = Relationship.Direction.OUTGOING)
//    public List<MaintenanceLog> maintenanceLogs;
//
//
//    @Relationship(type = "OWNED_BY", direction = Relationship.Direction.INCOMING)
//    public Person owner;

//
//    public Equipment() {
//        // This is needed for the REST-Easy JSON Binding
//    }
//
//    public Equipment(String name) {
//        this.name = name;
//    }

//    public Equipment(String id, String name) {
//        this.id = id;
//    }
//
//    public Equipment(
//        String id,
//        String name,
//        String manufacturer,
//        String model,
//        String serialNumber,
//        String productionDate,
//        String purchaseDate,
//        String specification,
//        String machineType,
//        String imageUri
//    ) {
//        this.id = id != null ? id : "";
//        this.name = name != null ? name : "";
//        this.manufacturer = manufacturer != null ? manufacturer : "";
//        this.model = model != null ? model : "";
//        this.serialNumber = serialNumber != null ? serialNumber : "";
//        this.specification = specification != null ? specification : "";
//        this.machineType = machineType != null ? machineType : "";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            this.productionDate = productionDate != null
//                ? sdf.parse(productionDate)
//                : null;
//            this.purchaseDate = purchaseDate != null
//                ? sdf.parse(purchaseDate)
//                : this.purchaseDate;
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        this.imageUri = imageUri != null ? imageUri : "";
//    }
//
//    public Equipment(
//        String id,
//        String name,
//        String manufacturer,
//        String model,
//        String serialNumber,
//        Date productionDate,
//        Date purchaseDate,
//        String specification,
//        String machineType,
//        String imageUri
//    ) {
//        this.id = id;
//        this.name = name;
//        this.manufacturer = manufacturer;
//        this.model = model;
//        this.serialNumber = serialNumber;
//        this.productionDate = productionDate;
//        this.purchaseDate = purchaseDate;
//        this.specification = specification;
//        this.machineType = machineType;
//        this.imageUri = imageUri;
//    }
//
//    // Create Equipment from Neo4J Node
//    public static Equipment from(Node node) {
//        return new Equipment(
//            String.valueOf(node.get("id")),
//            node.get("name").isNull() ? null : node.get("name").asString(),
//            node.get("manufacturer").isNull()
//                ? null
//                : node.get("manufacturer").asString(),
//            node.get("model").isNull() ? null : node.get("model").asString(),
//            node.get("serialNumber").isNull()
//                ? null
//                : node.get("serialNumber").asString(),
//            node.get("productionDate").isNull()
//                ? null
//                : node.get("productionDate").asString(),
//            node.get("purchaseDate").isNull()
//                ? null
//                : node.get("purchaseDate").asString(),
//                // Add specification field
//            node.get("specification").isNull()
//                ? null
//                : node.get("specification").asString(),
//                // Add machineType field
//            node.get("type").isNull()
//                ? null
//                : node.get("type").asString(),
//                // Add imageUri field
//            node.get("imageUri").isNull()
//                ? null
//                : node.get("imageUri").asString()
//
//
//
//        );
//    }
}
