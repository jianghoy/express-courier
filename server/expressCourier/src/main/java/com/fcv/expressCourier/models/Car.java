package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "car")
public class Car extends Robot implements Serializable {
    private static final long serialVersionUID = 179618413781514686L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @OneToOne
    int EstimateTimeArrival;
    @OneToOne
    int status;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    List<Order> deliverOrders;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    List<Item> pickupItems;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
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
