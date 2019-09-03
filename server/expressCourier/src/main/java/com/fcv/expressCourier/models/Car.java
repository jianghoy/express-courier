package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;

@Entity
public class Car extends Robot implements Serializable {
    private static final long serialVersionUID = 179618413781514686L;


    int battery;

    @OneToMany(mappedBy = "robot", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<DeliveryOrder> deliverOrders;
//    @OneToMany(mappedBy = "robot", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    List<Item> pickupItems;
    @OneToMany(mappedBy = "robot", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<DeliveryOrder> pickupOrders;


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
    public void pickUp(Item item, DeliveryOrder order) {

    }

    @Override
    public void drop(Item item, DeliveryOrder order) {

    }

    public void charge() {

    }


}
