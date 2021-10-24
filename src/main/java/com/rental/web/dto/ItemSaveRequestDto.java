package com.rental.web.dto;

import com.rental.entity.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemSaveRequestDto { //상품 등록시 데이터 교환을 위한 객체 클래스
    private String itemName;
    private Integer price;
    private String itemDetail;
    private String fileName;
    private String uuid;
    private String contentType;

    @Builder //@Setter를 사용하지 않고 어떤 필드에 어떤 인자를 넣어줄 지 명확하게 설정하기 위해 빌더 패턴 사용
    public ItemSaveRequestDto(String itemName, Integer price, String itemDetail, String fileName, String uuid, String contentType) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.fileName = fileName;
        this.uuid = uuid;
        this.contentType = contentType;

    }

    public Item toEntity() { //ItemService에서 사용할 DTO 객체에서  Entity로 변환해주는 메소드
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .itemDetail(itemDetail)
                .fileName(fileName)
                .uuid(uuid)
                .contentType(contentType)
                .build();
    }
}
