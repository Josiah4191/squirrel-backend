package com.josiah.squirrels.stash.entity;

import com.josiah.squirrels.squirrel.entity.Squirrel;
import jakarta.persistence.*;

@Entity
@Table(name = "stash")
public class Stash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    @ManyToOne
    @JoinColumn(name = "squirrel_id")
    private Squirrel squirrel;

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Squirrel getSquirrel() {
        return squirrel;
    }

    public void setSquirrel(Squirrel squirrel) {
        this.squirrel = squirrel;
    }
}
