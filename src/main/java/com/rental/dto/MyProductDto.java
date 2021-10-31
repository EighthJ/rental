package com.rental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.domain.product.Product;
import com.rental.domain.user.dto.userDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MyProductDto {

    @JsonProperty("product_id")
    private Long product_id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;
    @JsonProperty("price")
    private int price;
    @JsonProperty("charge")
    private int charge;

    public MyProductDto(Product product){
        this.product_id = product.getId();
        this.title = product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.charge = product.getCharge();
    }
}
