package com.fcv.expressCourier.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Drone extends Robot{
    @Id
    //TODO: DECIDE SCHEMA AND HOOK UP EVERYTHING
    //@OneToMany
    private long id;
    private Item currentItem;
    private Item pickupItem;
    private int price;
    private int EstimateTimeArrival;
    private int status;
    private Order deliverOrder;
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
    public void setCapcity(int capacity) {

    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public void setSpeed(int speed) {

    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
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
