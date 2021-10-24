package com.rental.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor //기본 생성자 자동 추가(public posts () {}와 같은 효과)
@ToString //tostring 메소드 자동 생성
@Getter
@Table(name="item") //생략해도 되지만 테이블의 이름을 item으로 지정하기 위해 사용
@Entity //테이블과 링크될 클래스임을 나타냄
public class Item { //상품으 실제 데이터가 저장되는 Entity

    @Id //해당 테이블의 PK 필드를 나타냄
    @Column(name="item_id") //테이블의 칼럼을 나타냄(없어도 되지만 기본값 외에 추가로 변경이 필요한 옵션(item_id)을 사용하기 위해 명시함)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(name="price", nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String itemDetail;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String contentType;

    @Builder //해당 클래스의 빌더 패턴 클래스 생성
    public Item(String itemName, Integer price, String itemDetail, String fileName, String uuid, String contentType) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.fileName = fileName;
        this.uuid = uuid;
        this.contentType = contentType;

    }

    public void update(String itemName, Integer price, String itemDetail, String fileName, String uuid, String contentType) {
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.fileName = fileName;
        this.uuid = uuid;
        this.contentType = contentType;
    }
}
