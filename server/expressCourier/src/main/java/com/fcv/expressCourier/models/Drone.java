package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Drone extends Robot implements Serializable {
    private static final long serialVersionUID = 8161569630798667789L;
    @Id
    //TODO: DECIDE SCHEMA AND HOOK UP EVERYTHING
    private long id;
    @OneToOne
    private Item currentItem;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private DeliveryOrder deliverOrder;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private DeliveryOrder pickupOrder;

    public void setId(long id) {
        this.id = id;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    public void getPickupItem() {
    }

    public void setPickupItem(Item pickupItem) {
        this.currentItem = pickupItem;
    }


    @Override
    public int getSpeed() {
        return 0;
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
    public void setSpeed(int speed) {

    }


    @Override
    public void pickUp(Item item, DeliveryOrder order) {
        currentItem = item;
        pickupOrder = order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public void charge() {
    }

    @Override
    public void drop(Item item, DeliveryOrder order) {

    }
}
