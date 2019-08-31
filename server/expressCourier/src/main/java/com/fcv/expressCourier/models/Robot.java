package com.fcv.expressCourier.models;

import javax.persistence.Entity;

@Entity
public abstract class Robot {
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public abstract int getSpeed();

    public abstract void setMaxWeight(int weight);

    public abstract int getMaxWeight();

    public abstract void setCapcity(int capacity);

    public abstract int getCapacity();

    public abstract void setSpeed(int speed);

    public abstract int getPrice();

    public abstract void setPrice(int price);

    public abstract void pickUp(Item item, Order order);

    public abstract void drop(Item item, Order order);
}
