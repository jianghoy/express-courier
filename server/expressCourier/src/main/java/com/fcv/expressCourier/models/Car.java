package com.fcv.expressCourier.models;

import java.util.List;

public class Car extends Robot {
    int id;
    int price;
    int EstimateTimeArrival;
    int status;
    List<Order> deliverOrders;
    List<Item> pickupItems;
    List<Order> pickupOrders;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
        pickupItems.add(item);
        pickupOrders.add(order);
    }

    @Override
    public void drop(Item item, Order order) {

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

    public List<Order> getDeliverOrders() {
        return deliverOrders;
    }

    public void setDeliverOrders(List<Order> deliverOrders) {
        this.deliverOrders = deliverOrders;
    }



}
