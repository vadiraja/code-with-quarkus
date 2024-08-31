package com.vadionline.equip.entities;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDate;
import java.util.Date;

@NodeEntity
public class MaintenanceLog  {
    public String description;
    public String status;
    public Date date;
    public String logId;
    public String type;
    public Float cost;

    //add an equipmentId field and associate with Equipment

    @Relationship(type = "HAS_LOG", direction = Relationship.Direction.INCOMING)
    public Equipment equipment;

    public MaintenanceLog() {
        // This is needed for the REST-Easy JSON Binding
    }





}
