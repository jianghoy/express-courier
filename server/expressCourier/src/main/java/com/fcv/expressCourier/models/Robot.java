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

    // deliver or pickup, status = 0 deliver, status = 1 pickup
    int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public abstract int getSpeed();

    public abstract void setSpeed(int speed);

    public abstract void setMaxWeight(int weight);

    public abstract int getMaxWeight();

    public abstract void setCapacity(int capacity);

    public abstract int getCapacity();

    public abstract void pickUp(Item item, DeliveryOrder order);

    public abstract void drop(Item item, DeliveryOrder order);

    public abstract void charge();
}
