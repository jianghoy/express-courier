package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "robot")
public class Robot implements Serializable {
    private static final long serialVersionUID = 4967845126404931447L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Order currentOrder;

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public List<Order> getPrevOrder() {
        return prevOrder;
    }

    public void setPrevOrder(List<Order> prevOrder) {
        this.prevOrder = prevOrder;
    }

    public List<Order> getTodoOrder() {
        return todoOrder;
    }

    public void setTodoOrder(List<Order> todoOrder) {
        this.todoOrder = todoOrder;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @OneToMany
    private List<Order> prevOrder;

    @OneToMany
    private List<Order> todoOrder;



    /**
     * type: 0:car or 1:drone
     */
    private int type;

    private double lat;
    private double lon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
