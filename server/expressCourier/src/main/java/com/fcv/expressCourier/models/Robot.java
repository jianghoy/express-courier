package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date estimatedIdleTime;

    /**
     * type: 0:car or 1:drone
     */
    private int type;

    private double lat;
    private double lon;
    @OneToMany
    private List<Order> prevOrder;

    @OneToMany
    private List<Order> todoOrder;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WareHouse wareHouse;

    @Temporal(TemporalType.TIMESTAMP)
    private Date posTime;


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


    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    public Date getPosTime() {
        return posTime;
    }

    public void setPosTime(Date posTime) {
        this.posTime = posTime;
    }

    public Date getEstimatedIdleTime() {
        return estimatedIdleTime;
    }

    public void setEstimatedIdleTime(Date estimatedIdleTime) {
        this.estimatedIdleTime = estimatedIdleTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
