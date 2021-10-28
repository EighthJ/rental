package com.rental.controller;

import com.rental.domain.reservation.dto.reserveDto;
import com.rental.service.reservation.reservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class reserveController {

    /*private final reservationService reservationService;

    @PostMapping("/api/reserve/{productId}")
    public void doReserve(@AuthenticationPrincipal UserDetails currentUser,
                          @RequestBody reserveDto.request requestDto,
                          @PathVariable Long productId) {
        reservationService.reserve(currentUser, requestDto, productId);
    }

     */
}
