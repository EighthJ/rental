package com.rental.repository;

import com.rental.entity.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @AfterEach
    public void cleanup() {
        itemRepository.deleteAll();
    }

    @Test
    @DisplayName("상품 등록 테스트")
    public void createItemTest() {
        String itemName = "상품 이름";
        String price = "가격";
        String itemDetail = "상품 상세설명";

        itemRepository.save(Item.builder()
                .itemName(itemName)
                .price(10000)
                .itemDetail("ssss")
                .build());

    }
}