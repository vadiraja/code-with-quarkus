package com.vadionline.equip.entities;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@NodeEntity
public class MaintenanceLog  {
    @Id
    @GeneratedValue
    public Long id;
    public String description;
    public String status;
    public LocalDate date;
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
