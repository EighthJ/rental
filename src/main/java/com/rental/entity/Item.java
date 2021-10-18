package com.rental.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@ToString
@Getter
@Table(name="item")
@Entity
public class Item {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(name="price", nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String itemDetail;

    @Builder
    public Item(String itemName, Integer price, String itemDetail) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;

    }

    public void update(String itemName, Integer price, String itemDetail) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
    }
}
