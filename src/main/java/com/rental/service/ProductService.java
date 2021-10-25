package com.rental.service;

import com.rental.domain.product.Product;
import com.rental.domain.user.User;
import com.rental.dto.*;
import com.rental.repository.ProductRepository;
import com.rental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public void createProduct(ProductSaveDto saveDto, Long id){
        Product uploadProduct = getPro(saveDto,id);
        Product saveProduct = productRepository.save(uploadProduct);

    }

    private Product getPro(ProductSaveDto productSaveDto, Long id){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당게시물이 없습니다. id" +id));
        return Product.from(
                productSaveDto.getTitle(),
                productSaveDto.getContent(),
                productSaveDto.getPrice(),
                productSaveDto.getCharge(),
                user
        );
    }

    public List<Product> findAllItems(){
        return productRepository.findAll();
    }

    public void update(Long id, ProductUpdateRequestDto requestDto){
        Product product = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당게시물이 없습니다. id" +id));
        product.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getPrice(), requestDto.getCharge());
        productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당게시물이 없습니다. id" +id));
    }

    //등록자
    public List<ProductDto> userSearch(Long user_id){
        User user = userRepository.findById(user_id).orElseThrow(()-> new IllegalArgumentException("해당게시물이 없습니다. id" +user_id));
        List<Product> productList = productRepository.findAllByUser(user);
        List<ProductDto> DtoList = new ArrayList<>();

        if(productList.isEmpty()) return DtoList;

        for(Product product : productList){
            DtoList.add(new ProductDto(product));
        }
        return DtoList;
    }


    //By타이틀
    public List<ProductDto> titleSearch(String keyword){
        List<Product> productList = productRepository.findByTitleContaining(keyword);
        List<ProductDto> DtoList = new ArrayList<>();

        if(productList.isEmpty()) return DtoList;

        for(Product product : productList){
            DtoList.add(new ProductDto(product));
        }
        return DtoList;
    }

    //By내용
    public List<ProductDto> contentSearch(String keyword){
        List<Product> productList = productRepository.findByContentContaining(keyword);
        List<ProductDto> DtoList = new ArrayList<>();

        if (productList.isEmpty()) return DtoList;

        for (Product product : productList){
            DtoList.add(new ProductDto(product));
        }
        return DtoList;
    }



}