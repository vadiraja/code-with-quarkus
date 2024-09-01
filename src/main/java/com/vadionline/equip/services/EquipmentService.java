package com.vadionline.equip.services;

import com.vadionline.equip.entities.Equipment;
import com.vadionline.equip.entities.MaintenanceLog;
import jakarta.enterprise.context.ApplicationScoped;
import org.neo4j.ogm.session.SessionFactory;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@ApplicationScoped
public class EquipmentService {
    @Inject
    SessionFactory sessionFactory;

    public EquipmentService() {
    }

    public Equipment getEquipment(Long id) {

        return sessionFactory.openSession().load(Equipment.class, id);

    }
    public Equipment getEquipmentByEid(Long eid) {
        //Get Equipment
//         sessionFactory.openSession().queryForObject(Equipment.class, "MATCH (e:Equipment {id:$eid} )  RETURN e", Map.of("eid", eid));

         //Add owner and Maintenance logs
        Equipment equipment =  sessionFactory.openSession().queryForObject(Equipment.class,
                "MATCH (e:Equipment {id:$eid} )"
                        +"OPTIONAL MATCH (e)<-[r:USES]-(p:Person) RETURN e,p", Map.of("eid", eid));
        System.out.println("Equipment: " + equipment.getUsers());
        return equipment;

    }

    public void saveEquipment(Equipment equipment) {
        sessionFactory.openSession().save(equipment);
    }

    public void updateEquipment(Equipment equipment) {
        sessionFactory.openSession().save(equipment);
    }

    public void deleteEquipment(String id) {
        sessionFactory.openSession().delete(sessionFactory.openSession().load(Equipment.class, id));
    }

    public void deleteAllEquipment() {
        sessionFactory.openSession().deleteAll(Equipment.class);
    }

    public List<Equipment> getAllEquipment() {

        try {

            return (List<Equipment>) sessionFactory.openSession().loadAll(Equipment.class, 1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //GET Maintenance logs for an equipment
    public List<MaintenanceLog> getMaintenanceLogs(Long id) {
//        Equipment equipment = getEquipmentByEid(id);
        Equipment equipment = getEquipment(id);
//        String query = "MATCH (e:Equipment {id:$eid})-[:HAS_LOG]->(l:MaintenanceLog) RETURN l";
        //Write to a query to get equipment by elementid
        String query = "MATCH (e:Equipment) WHERE id(e) = $id MATCH (e)-[:HAS_LOG]->(l:MaintenanceLog) RETURN l";
        List<MaintenanceLog> logs = (List<MaintenanceLog>) sessionFactory.openSession().query(MaintenanceLog.class,
                query, Map.of("id", id));
        System.out.println("Logs: " + logs);
        return logs;
    }
}
