package com.rental.repository;

import com.rental.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository를 상속하는 DB Layer 접근자(CRUD 메소드 자동 생성)
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByName(String itemName);
    List<Item> findAll();

}
