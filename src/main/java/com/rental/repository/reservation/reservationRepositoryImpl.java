package com.rental.repository.reservation;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rental.domain.reservation.reservation;
import com.rental.domain.reservation.reserveStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.rental.domain.reservation.Qreservation.reservation;

@RequiredArgsConstructor
public class reservationRepositoryImpl implements reservationRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<reservation> getAllReserveList(Long productId) {
        return queryFactory.select(reservation)
                .from(reservation)
                .join(reservation.product).fetchJoin()
                .join(reservation.user).fetchJoin()
                .where(reservation.reserveStatus.eq(reserveStatus.RENTAL)
                        .and(reservation.product.id.eq(productId)))
                .fetch();
    }

    @Override
    public List<reservation> getAllReserveListToAllow(Long userId) {

        return queryFactory.select(reservation)
                .from(reservation)
                .join(reservation.product).fetchJoin()
                .where(reservation.product.user.id.eq(userId)
                        .and(reservation.reserveStatus.eq(reserveStatus.GENERAL)))
                .fetch();
    }

    @Override
    public Optional<reservation> getProductToAllow(Long userId, Long productId) {
        return Optional.ofNullable(queryFactory.select(reservation)
                .from(reservation)
                .join(reservation.product).fetchJoin()
                .where(reservation.user.id.eq(userId)
                        .and(reservation.product.id.eq(productId))
                        .and(reservation.reserveStatus.eq(reserveStatus.GENERAL)))
                .fetchOne());
    }

    @Override
    public Optional<reservation> getProductToReturn(Long userId, Long productId) {
        return Optional.ofNullable(queryFactory.select(reservation)
                .from(reservation)
                .join(reservation.product).fetchJoin()
                .where(reservation.user.id.eq(userId)
                        .and(reservation.product.id.eq(userId))
                        .and(reservation.reserveStatus.eq(reserveStatus.RENTAL)))
                .fetchOne());
    }

    @Override
    public List<reservation> reserveRequestForBuyer(Long userId) {
        return queryFactory.select(reservation)
                .from(reservation)
                .join(reservation.product).fetchJoin()
                .where(reservation.user.id.eq(userId))
                .fetch();
    }

    @Override
    public List<reservation> reserveRequestForBuyerToAllow(Long userId) {
        return queryFactory.select(reservation)
                .from(reservation)
                .join(reservation.product).fetchJoin()
                .where(reservation.user.id.eq(userId)
                        .and(reservation.reserveStatus.eq(reserveStatus.RENTAL)))
                .fetch();
    }

    @Override
    public Boolean reserveExists(Long userId, Long productId) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(reservation)
                .where(reservation.user.id.eq(userId)
                        .and(reservation.product.id.eq(productId))
                        .and(reservation.reserveStatus.eq(reserveStatus.RENTAL)))
                .fetchFirst();

        return fetchOne != null;
    }
}
