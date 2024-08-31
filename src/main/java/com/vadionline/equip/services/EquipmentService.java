package com.vadionline.equip.services;

import com.vadionline.equip.entities.Equipment;
import jakarta.enterprise.context.ApplicationScoped;
import org.neo4j.ogm.session.SessionFactory;
import jakarta.inject.Inject;

import java.util.Collection;


@ApplicationScoped
public class EquipmentService {
    @Inject
    SessionFactory sessionFactory;

    public EquipmentService() {
    }

    public Equipment getEquipment(String id) {

        return sessionFactory.openSession().load(Equipment.class, id);

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

    public Equipment[] getAllEquipment() {
        Equipment[] array;
        try {
            array = (Equipment[]) sessionFactory.openSession().loadAll(Equipment.class).toArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        array = (Equipment[]) sessionFactory.openSession().loadAll(Equipment.class).toArray();

        return array;
    }
}
