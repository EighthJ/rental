package com.rental.controller;


import com.rental.domain.product.Product;
import com.rental.dto.*;
import com.rental.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    //수정필요
    @PostMapping("/product/post")
    public void create(@RequestBody ProductSaveDto saveDto, @AuthenticationPrincipal UserDetails currentUser){
        productService.createProduct(saveDto, currentUser);
    }

    //완료
    @GetMapping("/product/all")
    public List<ProductDto> findAll(){
        return productService.findAll();
    }

    //상푸ID로 조회
    @GetMapping("/product/{product_id}")
    public ProductDto getProduct(@PathVariable Long product_id){
        return new ProductDto(productService.findById(product_id));
    }

    //수정
    @PutMapping("/product/update/{product_id}")
    public void update(@PathVariable Long product_id, @RequestBody ProductUpdateRequestDto requestDto, @AuthenticationPrincipal UserDetails currentUser){
        productService.update(product_id,requestDto,currentUser);
    }

    //품명조회
    @GetMapping("product/title")
    public List<ProductDto> gettitle(@RequestParam String title){
        {
            return productService.titleSearch(title);
        }
    }

    //내용으로 조회
    @GetMapping("/product/content")
    public List<ProductDto> getcontent(@RequestParam String content){
        return productService.contentSearch(content);
    }


    //로그인된 유저가 등록한 상품
    @GetMapping("/product/byuser")
    public List<MyProductDto> getuser( @AuthenticationPrincipal UserDetails currentUser){
        return productService.userSearch(currentUser);
    }

    //삭제
    @DeleteMapping("product/delete/{product_id}")
    void deleteItem(@PathVariable Long product_id, @AuthenticationPrincipal UserDetails currentUser){
        productService.deleteById(product_id, currentUser);
    }

}
