package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "car")
public class Car extends Robot implements Serializable {
    private static final long serialVersionUID = 179618413781514686L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    // deliver or pickup, status = 0 deliver, status = 1 pickup
    int status;
    int battery;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    List<Order> deliverOrders;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    List<Item> pickupItems;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    List<Order> pickupOrders;


    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void setSpeed(int speed) {

    }

    @Override
    public void setMaxWeight(int weight) {

    }

    @Override
    public int getMaxWeight() {
        return 0;
    }

    @Override
    public void setCapacity(int capacity) {

    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public void pickUp(Item item, Order order) {

    }

    @Override
    public void drop(Item item, Order order) {

    }

    public void charge() {

    }


}
