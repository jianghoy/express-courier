package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.Car;
import com.fcv.expressCourier.models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarDaoImpl implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Car getCar(int carId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Car car = (Car) session.get(Car.class, carId);
            session.getTransaction().commit();
            return car;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
