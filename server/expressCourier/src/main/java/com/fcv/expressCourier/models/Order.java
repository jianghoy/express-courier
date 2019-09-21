package com.fcv.expressCourier.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
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
    @ManyToOne
    Robot robot;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany
    List<Item> items;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    private Customer customer;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Address shippingAddress;

//    @OneToOne
//    private WareHouse wareHouse;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Address pickUpAddress;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Address billingAddress;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

//    public WareHouse getWareHouse() {
//        return wareHouse;
//    }
//
//    public void setWareHouse(WareHouse wareHouse) {
//        this.wareHouse = wareHouse;
//    }

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
