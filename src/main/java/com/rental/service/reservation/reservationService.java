package com.rental.service.reservation;

import com.rental.domain.product.Product;
import com.rental.domain.reservation.dto.reserveDto;
import com.rental.domain.reservation.reservation;
import com.rental.domain.reservation.reserveStatus;
import com.rental.domain.user.User;
import com.rental.exception.CannotReserveExceptioni;
import com.rental.exception.ProductNotFoundException;
import com.rental.repository.ProductRepository;
import com.rental.repository.reservation.reservationRepository;
import com.rental.repository.user.UserRepository;
import com.rental.util.AboutDate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class reservationService {

    private final reservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public reserveDto.Info reserve(UserDetails currentUser, reserveDto.request requestDto, Long productId) {

        if(!reserve_OK(requestDto, productId)) {
            throw new CannotReserveExceptioni("이미 예약된 날짜입니다.");
        }

        User user = userRepository.findByEmail(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));

        if(reservationRepository.reserveExists(user.getId(), productId)) {
            throw new CannotReserveExceptioni("해당 상품의 승인되지 않은 신청내역이 이미 존재합니다.");
        }

        if(user.getRole().getKey().equals("SELLER")) {
            throw new CannotReserveExceptioni("구매자만 예약이 가능합니다.");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("해당 상품은 존재하지 않습니다."));

        return new reserveDto.Info(reservationRepository.save(reservation.builder()
                .user(user)
                .product(product)
                .startDate(AboutDate.getLocalDateTimeFromString(requestDto.getStartDate()))
                .finalDate(AboutDate.getLocalDateTimeFromString(requestDto.getFinalDate()))
                .totalPrice(product.getPrice() * AboutDate.compareDay(requestDto.getStartDate(), requestDto.getFinalDate()))
                .RealStartDate(AboutDate.getLocalDateTimeFromString(requestDto.getStartDate()))
                .RealFinalDate(AboutDate.getLocalDateTimeFromString(requestDto.getFinalDate()))
                .reserveStatus(reserveStatus.GENERAL)
                .build()));
    }

    public boolean reserve_OK(reserveDto.request request,Long productId) {

        List<reserveDto.booked> books = getAllBookingList(productId);

        for (reserveDto.booked book : books) {
            if (AboutDate.compareDay(request.getFinalDate(), book.getRealStartDate()) >= 1 ||
                    AboutDate.compareDay(book.getRealFinalDate(), request.getStartDate()) >= 1) {
                continue;
            }
            return false;
        }
        return true;
    }

    public List<reserveDto.booked> getAllBookingList(Long productId) {
        List<reservation> books = reservationRepository.getAllReserveList(productId);
        List<reserveDto.booked> finalList = new ArrayList<>();
        for (reservation book : books) finalList.add(new reserveDto.booked(book));

        return finalList;
    }

    public List<reserveDto.Info> getAllowList(UserDetails currentUser) {
        User user = userRepository.findByEmail(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));

        return reservationRepository.getAllReserveListToAllow(user.getId())
                .stream().map(r -> new reserveDto.Info(r)).collect(Collectors.toList());
    }

    @Transactional
    public reserveDto.Info allowReserve(Long userId, Long productId) {

        reservation reservation = reservationRepository.getProductToAllow(userId, productId)
                .orElseThrow(() -> new ProductNotFoundException("예약 내역이 없습니다."));

        reservation.changeReserveStatus();

        return new reserveDto.Info(reservation);
    }

    @Transactional
    public reserveDto.Info returnReserve(Long userId, Long productId) {

        reservation reservation = reservationRepository.getProductToReturn(userId, productId)
                .orElseThrow(() -> new ProductNotFoundException("예약 내역이 없습니다."));

        reservation.changeReserveStatus();

        return new reserveDto.Info(reservation);
    }

    public List<reserveDto.booked> getMyReserveRequestList(UserDetails currentUser) {

        User user = userRepository.findByEmail(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));

        return reservationRepository.reserveRequestForBuyer(user.getId())
                .stream().map(r -> new reserveDto.booked(r)).collect(Collectors.toList());
    }

    public List<reserveDto.booked> getMyReserveRequestListAllow(UserDetails currentUser) {
        User user = userRepository.findByEmail(currentUser.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));

        return reservationRepository.reserveRequestForBuyerToAllow(user.getId())
                .stream().map(r -> new reserveDto.booked(r)).collect(Collectors.toList());
    }
}
