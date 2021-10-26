package com.rental.dto;

import com.rental.domain.product.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rental.entity.User.dto.userDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;
    @JsonProperty("price")
    private int price;
    @JsonProperty("charge")
    private int charge;
    @JsonProperty("user")
    private userDto.Info user;

    public ProductDto(Product product){
        this.title = product.getTitle();
        this.title = product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.user = new userDto.Info(product.getUser());
    }
}
