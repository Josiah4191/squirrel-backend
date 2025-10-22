package com.josiah.squirrels.stash.entity;

import com.josiah.squirrels.squirrel.entity.Squirrel;
import com.josiah.squirrels.stashline.entity.StashLine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "stash")
public class Stash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=5, max=255, message="Location must be between 5 and 30 characters")
    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "squirrel_id")
    private Squirrel squirrel;

    @OneToMany(mappedBy = "stash", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StashLine> stashLineList;

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

    public List<StashLine> getStashLineList() {
        return stashLineList;
    }

    public void setStashLineList(List<StashLine> stashLineList) {
        this.stashLineList = stashLineList;
    }
}
