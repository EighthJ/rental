package com.rental.domain.user;

import com.rental.domain.BaseTimeEntity;
import com.rental.domain.product.Product;
import com.rental.domain.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @NotNull
    private String password;
    @NotNull
    private String nickname; // 닉네임
    @NotNull
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    private String address;
    private String name; // 실제 이름
    private String userIntro;

    public void changeRole() {
        this.role = (this.role == Role.BUYER) ? Role.SELLER : Role.BUYER;
    }

    public void update(com.rental.domain.user.dto.userDto.update updateDto) {
        this.name = updateDto.getName();
        this.address = updateDto.getAddress();
        this.nickname = updateDto.getNickname();
        this.userIntro = updateDto.getUserIntro();
    }
}
