package com.rental.dto;

import com.rental.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSaveDto {
    private String title;
    private String content;
    private int price;
    private int charge;
    private String fileName;
    private String filePath;

    public ProductSaveDto(){}
    public ProductSaveDto(Product product){
        this.title = product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.charge = product.getCharge();
        this.fileName = product.getFileName();
        this.filePath = product.getFilePath();
    }
}
