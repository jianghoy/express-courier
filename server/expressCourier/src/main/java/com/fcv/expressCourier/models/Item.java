package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private int weight;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private double price;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private int[] dimension = new int[3];

    public void setDimension(int length, int width, int height) {
        dimension[0] = length;
        dimension[1] = width;
        dimension[2] = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getSize() {
        return dimension;
    }

    public int getWight() {
        return this.weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
