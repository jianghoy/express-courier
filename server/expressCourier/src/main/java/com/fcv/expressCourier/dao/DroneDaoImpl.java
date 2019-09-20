package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.Drone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DroneDaoImpl implements  DroneDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Drone getDrone(int droneId) {
        Integer status = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Drone drone = session.get(Drone.class, droneId);
            session.getTransaction().commit();
            return drone;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
