package com.rental.domain.product;

import com.rental.domain.BaseTimeEntity;
import com.rental.domain.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private int price;

    @Column
    private int charge;

    @Column
    private String content;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //@Builder
    public Product(String title, String content, int price, int charge, User user){
        this.title = title;
        this.content = content;
        this.price = price;
        this.charge = charge;
        this.user = user;

    }

    public void update(String title, String content, int price, int charge){
        this.title = title;
        this.content = content;
        this.price = price;
        this.charge = charge;
        //this.fileId = fileId;
    }

    public static Product from(String title, String content, int price, int charge, User user){
        return new Product(title,content,price,charge,user);
    }
}
