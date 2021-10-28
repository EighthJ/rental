package com.rental.service;

import com.rental.domain.product.Product;
import com.rental.domain.user.Role;
import com.rental.domain.user.User;
import com.rental.dto.*;
import com.rental.repository.ProductRepository;
import com.rental.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public MyProductDto createProduct(ProductSaveDto saveDto, UserDetails currentUser) throws Exception{

        User user = userRepository.findByEmail(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));

        if (!(user.getRole() == Role.SELLER)) throw new ArithmeticException("게시 권한이 없습니다");

        Product uploadProduct = getPro(saveDto,user.getId());
        Product saveProduct = productRepository.save(uploadProduct);
        return new MyProductDto(saveProduct);
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

    @Transactional
    public void update(Long id, ProductUpdateRequestDto requestDto, UserDetails currentUser){
        User user = userRepository.findByEmail(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));

        Product product = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당게시물이 없습니다. id" +id));

        if (!user.equals(product.getUser())) throw new ArithmeticException("해당게시물의 게시자가 아닙니다");
        product.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getPrice(), requestDto.getCharge());
    }

    //상품아이디로 삭제
    public void deleteById(Long id, UserDetails currentUser){
        User user = userRepository.findByEmail(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));

        Product product = productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당게시물이 없습니다. id" +id));
        if (!user.equals(product.getUser())) throw new ArithmeticException("해당게시물의 게시자가 아닙니다");
        productRepository.deleteById(id);

    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당게시물이 없습니다. id" +id));
    }

    //등록자
    public List<MyProductDto> userSearch(UserDetails currentUser){
        User user = userRepository.findByEmail(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));

        List<Product> productList = productRepository.findAllByUser(user);
        List<MyProductDto> DtoList = new ArrayList<>();

        if(productList.isEmpty()) return DtoList;

        for(Product product : productList){
            DtoList.add(new MyProductDto(product));
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