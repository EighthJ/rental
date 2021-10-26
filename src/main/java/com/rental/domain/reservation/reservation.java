package com.rental.domain.reservation;

import com.rental.domain.BaseTimeEntity;
import com.rental.domain.product.Product;
import com.rental.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class reservation extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_Id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime startDate; // 예약 희망일
    private LocalDateTime finalDate; // 반납 희망일
    private LocalDateTime RealStartDate; // 실제 예약일
    private LocalDateTime RealFinalDate; // 실제 반납일
    private reserveStatus reserveStatus; // 예약 상태
    private Integer totalPrice; // 총 가격(가격 + 추가요금)
}
