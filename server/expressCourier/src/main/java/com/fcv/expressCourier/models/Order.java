package com.fcv.expressCourier.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "deliveryOrder")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double price;
    private String status;
    // car or drone
    private String type;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    Robot robot;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @JoinColumn(name = "costomer_id")
    private Customer customer;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Address shippingAddress;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Address pickUpAddress;

    @Temporal(TemporalType.TIMESTAMP)
    private Date generatedTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expectedPickUpTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expectedDeliveryTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date actuallPickUpTimestamp;

    public Date getGeneratedTimestamp() {
        return generatedTimestamp;
    }

    public void setGeneratedTimestamp(Date generatedTimestamp) {
        this.generatedTimestamp = generatedTimestamp;
    }

    public Date getExpectedPickUpTimestamp() {
        return expectedPickUpTimestamp;
    }

    public void setExpectedPickUpTimestamp(Date expectedPickUpTimestamp) {
        this.expectedPickUpTimestamp = expectedPickUpTimestamp;
    }

    public Date getExpectedDeliveryTimestamp() {
        return expectedDeliveryTimestamp;
    }

    public void setExpectedDeliveryTimestamp(Date expectedDeliveryTimestamp) {
        this.expectedDeliveryTimestamp = expectedDeliveryTimestamp;
    }

    public Date getActuallPickUpTimestamp() {
        return actuallPickUpTimestamp;
    }

    public void setActuallPickUpTimestamp(Date actuallPickUpTimestamp) {
        this.actuallPickUpTimestamp = actuallPickUpTimestamp;
    }

    public Date getActualDeliveryTimestamp() {
        return actualDeliveryTimestamp;
    }

    public void setActualDeliveryTimestamp(Date actualDeliveryTimestamp) {
        this.actualDeliveryTimestamp = actualDeliveryTimestamp;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date actualDeliveryTimestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(Address pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
