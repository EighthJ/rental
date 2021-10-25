package com.rental.repository;


import com.rental.domain.product.Product;
import com.rental.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByContentContaining(String keyword);
    List<Product> findByTitleContaining(String keyword);
    List<Product> findAllByUser(User user);
}
