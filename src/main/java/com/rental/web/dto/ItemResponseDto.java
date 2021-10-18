package com.rental.web.dto;

import com.rental.entity.Item;
import lombok.Getter;

@Getter
public class ItemResponseDto {

    private Long id;
    private String itemName;
    private Integer price;
    private String itemDetail;

    public ItemResponseDto(Item entity) {
        this.id = entity.getId();
        this.itemName = entity.getItemName();
        this.price = entity.getPrice();
        this.itemDetail = entity.getItemDetail();
    }
}
