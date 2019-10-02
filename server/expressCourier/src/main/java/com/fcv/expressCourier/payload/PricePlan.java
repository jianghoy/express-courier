package com.fcv.expressCourier.payload;

import java.io.Serializable;
import java.util.Date;

public class PricePlan implements Serializable {

    private double price;
    private String type;
    private Date estimatedPickupTime;
    private Date estimatedDeliveryTime;

    public PricePlan(double price, String type, Date estimatedPickupTime, Date estimatedDeliveryTime) {
        this.price = price;
        this.type = type;
        this.estimatedPickupTime = estimatedPickupTime;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public Date getEstimatedPickupTime() {
        return estimatedPickupTime;
    }

    public void setEstimatedPickupTime(Date estimatedPickupTime) {
        this.estimatedPickupTime = estimatedPickupTime;
    }

    public Date getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(Date estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    PricePlan(double price, String type) {
        this.price = price;
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }
}

