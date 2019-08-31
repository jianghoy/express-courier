package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "drone")
public class Drone extends Robot implements Serializable {
    @Id
    //TODO: DECIDE SCHEMA AND HOOK UP EVERYTHING
    //@OneToMany
    private long id;
    private Item currentItem;
    private Item pickupItem;

    @OneToOne
    private int EstimateTimeArrival;
    @OneToOne
    private int status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order")
    private Order deliverOrder;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order")
    private Order pickupOrder;

    public void setId(long id) {
        this.id = id;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    public Item getPickupItem() {
        return pickupItem;
    }

    public void setPickupItem(Item pickupItem) {
        this.pickupItem = pickupItem;
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
    public void pickUp(Item item, Order order) {
        pickupItem = item;
        pickupOrder = order;
    }

    public int getEstimateTimeArrival() {
        return EstimateTimeArrival;
    }

    public void setEstimateTimeArrival(int estimateTimeArrival) {
        EstimateTimeArrival = estimateTimeArrival;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public void drop(Item item, Order order) {

    }
}
