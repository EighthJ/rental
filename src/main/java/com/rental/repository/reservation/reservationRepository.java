package com.rental.repository.reservation;

import com.rental.domain.reservation.reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface reservationRepository extends JpaRepository<reservation, Long>, reservationRepositoryCustom {
}
