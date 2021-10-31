package com.rental.controller;

import com.rental.domain.reservation.dto.reserveDto;
import com.rental.service.reservation.reservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class reserveController {

    private final reservationService reservationService;

    /**
     * 예약 api
     */
    @PostMapping("/api/reserve/{productId}")
    public void doReserve(@AuthenticationPrincipal UserDetails currentUser,
                          @RequestBody reserveDto.request requestDto,
                          @PathVariable Long productId) {
        reservationService.reserve(currentUser, requestDto, productId);
    }

    /**
     * 예약 승인을 기다리는 상품 리스트 조회
     */
    @GetMapping("/api/reserve/allowList")
    public List<reserveDto.Info> getAllowList(@AuthenticationPrincipal UserDetails currentUser) {
        return reservationService.getAllowList(currentUser);
    }

    /**
     * 예약 승인  api
     */
    @PostMapping("/api/reserve/{userId}/allow/{productId}")
    public reserveDto.Info allowReserve(@PathVariable Long userId, // 예약 대기자
                             @PathVariable Long productId) {
        return reservationService.allowReserve(userId, productId);
    }

    /**
     * 예약 반납 api
     */
    @PostMapping("/api/reserve/{userId}/return/{productId}")
    public reserveDto.Info returnReserve(@PathVariable Long userId, // 예약 대기자
                                        @PathVariable Long productId) {
        return reservationService.returnReserve(userId, productId);
    }

    /**
     * 내가 올린 상품중 한가지의 예약 내역 조회
     */
    @GetMapping("/api/reserve/list/{productId}")
    public List<reserveDto.booked> getReserveList(@PathVariable Long productId) {
        return reservationService.getAllBookingList(productId);
    }

    /**
     * 내가 예약 신청한 상품 리스트 조회(미승인)
     */
    @GetMapping("/api/reserve/request")
    public List<reserveDto.booked> getReserveRequestList(@AuthenticationPrincipal UserDetails currentUser) {
        return reservationService.getMyReserveRequestList(currentUser);
    }

    /**
     * 내가 예약 신청하고 승인된 상품 조회
     */
    @GetMapping("/api/reserve/request/allow")
    public List<reserveDto.booked> getReserveRequestListAllow(@AuthenticationPrincipal UserDetails currentUser) {
        return reservationService.getMyReserveRequestListAllow(currentUser);
    }
}
