package com.josiah.squirrels.stashline.entity;

import com.josiah.squirrels.item.entity.Item;
import com.josiah.squirrels.stash.entity.Stash;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Check;

@Entity
@Check(constraints = "quantity >= 0")
@Table(name = "stash_line", uniqueConstraints = @UniqueConstraint(columnNames = {"stash_id", "item_id"}))
public class StashLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stash_id")
    private Stash stash;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    @Column( nullable = false)
    private int quantity;

    public Long getId() {
        return id;
    }

    public Stash getStash() {
        return stash;
    }

    public void setStash(Stash stash) {
        this.stash = stash;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
