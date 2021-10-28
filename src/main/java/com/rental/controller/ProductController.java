package com.rental.controller;


import com.rental.domain.product.Product;
import com.rental.dto.*;
import com.rental.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
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
    public MyProductDto create(@RequestBody ProductSaveDto saveDto, @AuthenticationPrincipal UserDetails currentUser) throws Exception{
        return productService.createProduct(saveDto, currentUser);
    }

    //완료
    @GetMapping("/product/all")
    public List<Product> findAll(){
        return productService.findAll();
    }

    //i완료
    @GetMapping("/product/{product_id}")
    public ProductDto getProduct(@PathVariable Long product_id){
        return new ProductDto(productService.findById(product_id));
    }

    @PutMapping("/product/update/{product_id}")
    public MyProductDto update(@PathVariable Long product_id, @RequestBody ProductUpdateRequestDto requestDto, @AuthenticationPrincipal UserDetails currentUser){
        return productService.update(product_id,requestDto, currentUser);
    }

    //품명조회/완료
    @GetMapping("product/title")
    public List<ProductDto> gettitle(@RequestParam(value = "title") String title){
        {
            return productService.titleSearch(title);
        }
    }

    //내용으로 조회/완료
    @GetMapping("/product/content")
    public List<ProductDto> getcontent(@RequestParam(value = "content") String content){
        return productService.contentSearch(content);
    }


    //등록자로 조회/되긴하네
    @GetMapping("/product/byuser")
    public List<MyProductDto> getuser(@AuthenticationPrincipal UserDetails currentUser){
        return productService.userSearch(currentUser);
    }

    //삭제
    @DeleteMapping("/product/delete/{product_id}")
    void deleteItem(@AuthenticationPrincipal UserDetails currentUser, @PathVariable Long product_id){
        productService.deleteById(product_id, currentUser);
    }

}
