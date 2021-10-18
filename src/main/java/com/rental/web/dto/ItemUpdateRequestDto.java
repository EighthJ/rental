package com.rental.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemUpdateRequestDto {
    private String itemName;
    private Integer price;
    private String itemDetail;

    @Builder
    public ItemUpdateRequestDto(String itemName, Integer price, String itemDetail) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
    }

}
