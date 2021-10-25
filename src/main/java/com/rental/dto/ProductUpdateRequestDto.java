package com.rental.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {

    private String title;
    private String content;
    private int price;
    private int charge;
    //private Long fileId;

    @Builder
    public ProductUpdateRequestDto(String title, String content, int price, int charge){
        this.title = title;
        this.content = content;
        this.price = price;
        this.charge = charge;
        //this.fileId = fileId;
    }
}
