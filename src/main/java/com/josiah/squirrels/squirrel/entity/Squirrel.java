package com.josiah.squirrels.squirrel.entity;

import com.josiah.squirrels.stash.entity.Stash;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "squirrel")
public class Squirrel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=2, max=40, message="Name must be between 2 and 40 characters")
    @Column(length = 40, nullable = false)
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
