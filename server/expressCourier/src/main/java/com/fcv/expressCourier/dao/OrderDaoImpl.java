package com.fcv.expressCourier.dao;

import com.fcv.expressCourier.models.Car;
import com.fcv.expressCourier.models.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDaoImpl implements  OrderDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addSalesOrder(Order order) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Order getOrder(int orderId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Order order = session.get(Order.class, orderId);
            session.getTransaction().commit();
            return order;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
