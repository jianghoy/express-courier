package com.fcv.expressCourier.models;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    private int id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public Role(RoleName name) {
        this.name = name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
