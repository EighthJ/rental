package com.rental.web.dto;

import com.rental.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemSaveRequestDto {
    private String itemName;
    private Integer price;
    private String itemDetail;
    @Builder
    public ItemSaveRequestDto(String itemName, Integer price, String itemDetail) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
    }

    public Item toEntity() {
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .itemDetail(itemDetail)
                .build();
    }
}
