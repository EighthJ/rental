package com.rental.web.dto;

import com.rental.entity.Item;
import lombok.Getter;

@Getter
public class ItemResponseDto { //API응답을 위해 Entity 객체를 바로 주지 않고 DTO로 변환해서 응답하기 위한 클래스

    private Long id;
    private String itemName;
    private Integer price;
    private String itemDetail;

    public ItemResponseDto(Item entity) { //Entity의 필드 중 일부만 사용하므로 생성자로 Item entity를 받아 처리함
        this.id = entity.getId();
        this.itemName = entity.getItemName();
        this.price = entity.getPrice();
        this.itemDetail = entity.getItemDetail();
    }
}
