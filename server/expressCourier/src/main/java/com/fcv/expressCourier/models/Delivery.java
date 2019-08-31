package com.fcv.expressCourier.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Delivery implements Serializable {

    @Id
    private Long id;
    // TODO: HOOK UP WITH DRONE
//    @ManyToOne
//    @JoinColumn
//    private Long droneId;
    private String status;

    public Delivery(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(id, delivery.id) &&
                Objects.equals(status, delivery.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
