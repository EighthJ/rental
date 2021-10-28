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
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class booked {

        private String buyerName;
        private String buyerEmail;
        private String buyerAddress;

        private String productTitle;
        private Long productPrice;
        private String RealStartDate;
        private String RealFinalDate;

        public booked(reservation reservation) {
            this.buyerName = reservation.getUser().getName();
            this.buyerEmail = reservation.getUser().getEmail();
            this.buyerAddress = reservation.getUser().getAddress();
            this.productTitle = reservation.getProduct().getTitle();
            this.productPrice = reservation.getTotalPrice();
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

        private Long buyerId;
        private String buyerNickname;
        private Long productId;
        private String productTitle;
        private String productContent;
        private Integer charge;

        private String sellerNickName;

        private String startDate;
        private String finalDate;
        private Long totalPrice;
        private String RealStartDate;
        private String RealFinalDate;
        private String reserveStatus;

        public Info(reservation reservation) {
            this.buyerId = reservation.getUser().getId();
            this.buyerNickname = reservation.getUser().getNickname();
            this.productId = reservation.getProduct().getId();
            this.productTitle = reservation.getProduct().getTitle();
            this.productContent = reservation.getProduct().getContent();
            this.charge = reservation.getProduct().getCharge();
            this.sellerNickName = reservation.getProduct().getUser().getNickname();
            this.startDate = AboutDate.getStringFromLocalDateTime(reservation.getStartDate());
            this.finalDate = AboutDate.getStringFromLocalDateTime(reservation.getFinalDate());
            this.totalPrice = reservation.getTotalPrice();
            this.RealStartDate = AboutDate.getStringFromLocalDateTime(reservation.getRealStartDate());
            this.RealFinalDate = AboutDate.getStringFromLocalDateTime(reservation.getRealFinalDate());
            this.reserveStatus = reservation.getReserveStatus().getKey();
        }
    }
}
