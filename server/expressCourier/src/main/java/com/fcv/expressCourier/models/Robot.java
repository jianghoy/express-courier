package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "robot")
public abstract class Robot implements Serializable {
    private static final long serialVersionUID = 4967845126404931447L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public abstract int getSpeed();

    public abstract void setSpeed(int speed);

    public abstract void setMaxWeight(int weight);

    public abstract int getMaxWeight();

    public abstract void setCapacity(int capacity);

    public abstract int getCapacity();

    public abstract void pickUp(Item item, Order order);

    public abstract void drop(Item item, Order order);

    public abstract void charge();
}
