package com.ashjang.reservation.domain.repository;

import com.ashjang.reservation.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByStoreId(Long storeId);

    Optional<Reservation> findByPhoneAndId(String phone, Long id);

    List<Reservation> findAllByPhoneAndUsedRvIsTrue(String phone);
}
