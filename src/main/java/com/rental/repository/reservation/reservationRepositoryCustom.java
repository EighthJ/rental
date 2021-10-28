package com.rental.repository.reservation;

import com.rental.domain.reservation.reservation;

import java.util.List;
import java.util.Optional;

public interface reservationRepositoryCustom {

    /**
     * 판매자 : 내가 올린 한 상품의 예약 완료 내역(현재 예약 내역)을 가져온다.
     */
    List<reservation> getAllReserveList(Long productId);

    /**
     * 판매자 : 내가 올린 모든 상품의 예약 요청 내역(현재 승인을 기다리고 있는 예약요청들)을 가져온다.
     */
    List<reservation> getAllReserveListToAllow(Long userId);

    /**
     * 판매자 : 예약 승인 하려는 하나의 상품을 가져온다.
     */
    Optional<reservation> getProductToAllow(Long userId, Long productId);

    /**
     * 판매자 : 반납 처리 하려는 하나의 상품을 가져온다.
     */
    Optional<reservation> getProductToReturn(Long userId, Long productId);

    /**
     * 판매자 : 내가 올린 모든 상품을 리스트로 조회한다.(길미령)
     */

    /**
     * 구매자 : 내가 예약 신청한 상품을 리스트 조회한다.
     */
    List<reservation> reserveRequestForBuyer(Long userId);

    /**
     * 구매자 : 내가 예약 신청하고 승인 완료된 상품을 리스트로 조회한다.
     */
    List<reservation> reserveRequestForBuyerToAllow(Long userId);

    /**
     * 승인이 안된 예약 요청이 이미 있는지 확인한다.
     */
    Boolean reserveExists(Long userId, Long productId);

}
