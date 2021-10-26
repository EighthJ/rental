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
    private String fileName;
    private String filePath;
    //private Long fileId;

    @Builder
    public ProductUpdateRequestDto(String title, String content, int price, int charge, String fileName, String filePath){
        this.title = title;
        this.content = content;
        this.price = price;
        this.charge = charge;
        this.fileName = fileName;
        this.filePath = filePath;
        //this.fileId = fileId;
    }
}
