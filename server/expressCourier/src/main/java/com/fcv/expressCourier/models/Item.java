package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item")
public class Item implements Serializable {
    private static final long serialVersionUID = 3670886414868062603L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double weight;

    private double[] dimension = new double[3];

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    public double[] getDimension() {
        return dimension;
    }

    public void setDimension(double[] dimension) {
        this.dimension = dimension;
    }
}
