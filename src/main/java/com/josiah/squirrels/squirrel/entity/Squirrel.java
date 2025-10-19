package com.josiah.squirrels.squirrel.entity;

import com.josiah.squirrels.stash.entity.Stash;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "squirrel")
public class Squirrel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "squirrel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stash> stashList;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Stash> getStashList() {
        return stashList;
    }

    public void setStashList(List<Stash> stashList) {
        this.stashList = stashList;
    }


}
