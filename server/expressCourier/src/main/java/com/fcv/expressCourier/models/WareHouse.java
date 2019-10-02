package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class WareHouse implements Serializable {
    private static final long serialVersionUID = 3670886414868062603L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "warehouse_id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address wareHouseAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getWareHouseAddress() {
        return wareHouseAddress;
    }

    public void setWareHouseAddress(Address wareHouseAddress) {
        this.wareHouseAddress = wareHouseAddress;
    }

    public List<Robot> getRobotList() {
        return robotList;
    }

    public void setRobotList(List<Robot> robotList) {
        this.robotList = robotList;
    }

    @OneToMany(mappedBy = "wareHouse",cascade = CascadeType.ALL)
    private List<Robot> robotList;
}
