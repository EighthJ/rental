package com.rental.controller;


import com.rental.domain.product.Product;
import com.rental.dto.*;
import com.rental.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProdcutController {

    @Autowired
    private ProductService productService;

    //수정필요
    @PostMapping("/product")
    public void create(@RequestBody ProductSaveDto saveDto, @AuthenticationPrincipal UserDetails currentUser){
        productService.createProduct(saveDto, currentUser);
    }

    //완료
    @GetMapping("/product")
    public List<Product> findAll(){
        return productService.findAll();
    }

    //i완료
    @GetMapping("/product/{product_id}")
    public ProductDto getProduct(@PathVariable Long product_id){
        return new ProductDto(productService.findById(product_id));
    }

    //되긴되는 데 입력안한 게 다 0이 됨
    @PutMapping("/product/update/{product_id}")
    public void update(@PathVariable Long product_id, @RequestBody ProductUpdateRequestDto requestDto){
        productService.update(product_id,requestDto);
    }

    //품명조회/완료
    @GetMapping("product/title/{title}")
    public List<ProductDto> gettitle(@PathVariable String title){
        {
            return productService.titleSearch(title);
        }
    }

    //내용으로 조회/완료
    @GetMapping("/product/content/{content}")
    public List<ProductDto> getcontent(@PathVariable String content){
        return productService.contentSearch(content);
    }


    //되긴하네
    @GetMapping("/product/user/{user_id}")
    public List<ProductDto> getuser(@PathVariable Long user_id){
        return productService.userSearch(user_id);
    }

    //삭제/완료
    @DeleteMapping("/{product_id}")
    void deleteItem(@PathVariable Long product_id){
        productService.deleteById(product_id);
    }

}
