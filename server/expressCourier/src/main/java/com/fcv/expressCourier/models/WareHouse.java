package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class WareHouse implements Serializable {
    private static final long serialVersionUID = 3670886414868062603L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address wareHouseAddress;
}
