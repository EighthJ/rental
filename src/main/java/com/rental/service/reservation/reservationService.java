package com.rental.service.reservation;

import com.rental.domain.product.Product;
import com.rental.domain.reservation.dto.reserveDto;
import com.rental.domain.reservation.reservation;
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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class reservationService {

    private final reservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

//    @Transactional
//    public void reserve(UserDetails currentUser, reserveDto.request requestDto, Long productId) {
//
//        if(!reserve_OK(requestDto, productId)) {
//            throw new CannotReserveExceptioni("이미 예약된 날짜입니다.");
//        }
//
//        User user = userRepository.findByEmail(currentUser.getUsername())
//                .orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));
//
//        if(user.getRole().getKey().equals("SELLER")) {
//            throw new CannotReserveExceptioni("구매자만 예약이 가능합니다.");
//        }
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new ProductNotFoundException("해당 상품 존재하지 않습니다."));
//
//        reservationRepository.save(reservation.builder()
//                .startDate(AboutDate.getLocalDateTimeFromString(requestDto.getStartDate()))
//                .finalDate(AboutDate.getLocalDateTimeFromString(requestDto.getFinalDate()))
//                .totalPrice(10000)
//                .RealStartDate(AboutDate.getLocalDateTimeFromString(requestDto.getStartDate()))
//                .RealFinalDate(AboutDate.getLocalDateTimeFromString(requestDto.getFinalDate()))
//                .build());
//    }

//    public boolean reserve_OK(reserveDto.request request,Long productId) {
//
//        List<reserveDto.booked> books = getAllBookingList(productId);
//
//        for (reserveDto.booked book : books) {
//            if (AboutDate.compareDay(request.getFinalDate(), book.getRealStartDate()) >= 1 ||
//                    AboutDate.compareDay(book.getRealFinalDate(), request.getStartDate()) >= 1) {
//                continue;
//            }
//            return false;
//        }
//        return true;
//    }

    /**
     * QueryDSL 해야됨.
     */
//    public List<reserveDto.booked> getAllBookingList(Long productId) {
//        List<reservation> books = roomReservationRepository.getAllList(productId);
//
//        List<reserveDto.booked> finalList = new ArrayList<>();
//        for (reservation book : books) {
//            finalList.add(reserveDto.booked.builder()
//                    .RealStartDate(AboutDate.getStringFromLocalDateTime(book.getRealStartDate()))
//                    .RealFinalDate(AboutDate.getStringFromLocalDateTime(book.getRealFinalDate()))
//                    .build());
//        }
//
//        return finalList;
//    }

    /**
     * 가격 구하는 api
     * 반납 처리 api
     * 예약 취소 api(후순위)
     */
}
