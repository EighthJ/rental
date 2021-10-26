package com.rental.domain.reservation.dto;

import com.rental.domain.reservation.reservation;
import com.rental.util.AboutDate;
import lombok.*;

import java.time.LocalDateTime;

public class reserveDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    public static class request {
        String startDate;
        String finalDate;
        Integer totalPrice;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class booked {
        private String RealStartDate;
        private String RealFinalDate;

        public booked(reservation reservation) {
            this.RealStartDate = AboutDate.getStringFromLocalDateTime(reservation.getRealStartDate());
            this.RealFinalDate = AboutDate.getStringFromLocalDateTime(reservation.getRealFinalDate());
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {

        // 예약한 상품 정보(dto)
        private Long productId;

        // 판매자 정보
        private String sellerNickName;
        private String sellerIntro;

        // 예약 정보
        private String startDate;
        private String finalDate;
        private Integer totalPrice;
        private String RealStartDate;
        private String RealFinalDate;

        public Info(reservation reservation) {
            this.RealStartDate = AboutDate.getStringFromLocalDateTime(reservation.getRealStartDate());
            this.RealFinalDate = AboutDate.getStringFromLocalDateTime(reservation.getRealFinalDate());
        }
    }
}
