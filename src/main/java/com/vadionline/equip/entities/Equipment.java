package com.vadionline.equip.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.neo4j.ogm.annotation.*;

import java.util.List;

@NodeEntity (label = "Equipment")
public class Equipment {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    private Long id;

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    @Property(name = "id")
    private Long eid;


    @Property(name = "name")
    private String name;

    @Property(name = "manufacturer")
    private String manufacturer;

    @Property(name = "model")
    private String model;

    @Property(name = "serialNumber")
    private String serialNumber;

    @Property(name = "specification")
    private String specification;

    @Property(name = "machineType")
    private String machineType;

    @Property(name = "imageUri")
    private String imageUri;

    public List<Person> getUsers() {
        return users;
    }

    public void setUsers(List<Person> users) {
        this.users = users;
    }

    @Relationship(type = "USES", direction = Relationship.Direction.INCOMING)
    @JsonSerialize(using = PersonSerializer.class)
    private List<Person> users;

    // Default constructor
    public Equipment() {
    }

    // Constructor with all fields
//    public Equipment(Long id, String name, String manufacturer, String model, String serialNumber,  String specification, String machineType, String imageUri) {
    public Equipment(Long id, String name , Long eid, String manufacturer, String model, String serialNumber,  String specification, String machineType, String imageUri) {
        this.id = id;
        this.eid = eid;
        this.name = name;
        this.manufacturer = manufacturer;
        this.model = model;
        this.serialNumber = serialNumber;
        this.specification = specification;
        this.machineType = machineType;
        this.imageUri = imageUri;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

//    public List<MaintenanceLog> getMaintenanceLogs() {
//        return maintenanceLogs;
//    }
//
//    public void setMaintenanceLogs(List<MaintenanceLog> maintenanceLogs) {
//        this.maintenanceLogs = maintenanceLogs;
//    }
//

}