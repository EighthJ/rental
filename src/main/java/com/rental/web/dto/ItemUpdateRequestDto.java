package com.rental.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemUpdateRequestDto { //상품 수정시 데이터를 교환을 위한 객체 클래스
    private String itemName;
    private Integer price;
    private String itemDetail;
    private String fileName;
    private String uuid;
    private String contentType;

    @Builder
    public ItemUpdateRequestDto(String itemName, Integer price, String itemDetail, String fileName, String uuid, String contentType) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.fileName = fileName;
        this.uuid = uuid;
        this.contentType = contentType;
    }

}
