package com.fcv.expressCourier.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fcv.expressCourier.models.Robot;

import java.io.Serializable;
import java.util.Date;

public class PricePlan implements Serializable {

    private double price;
    private String type;
    private Date estimatedPickupTime;
    private Date estimatedDeliveryTime;
    private Date estimatedReturnTime;
    @JsonIgnore
    private Robot robot;

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEstimatedReturnTime() {
        return estimatedReturnTime;
    }

    public void setEstimatedReturnTime(Date estimatedReturnTime) {
        this.estimatedReturnTime = estimatedReturnTime;
    }

    public PricePlan() {}

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

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }
}

