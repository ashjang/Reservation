package com.ashjang.reservation.service;

import com.ashjang.reservation.client.UserClient;
import com.ashjang.reservation.domain.dto.ReservationDto;
import com.ashjang.reservation.domain.dto.StoreDto;
import com.ashjang.reservation.domain.model.Reservation;
import com.ashjang.reservation.domain.repository.ReservationRepository;
import com.ashjang.reservation.exception.CustomException;
import com.ashjang.reservation.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerReservationService {
    private final ReservationRepository reservationRepository;
    private final UserClient userClient;

    // 예약 확인
    public List<Reservation> getReservation(Long storeId, String token) {
        // storeId로 예약된 목록
        List<Reservation> reservations = reservationRepository.findAllByStoreId(storeId);
        if (reservations.isEmpty()) {
            throw new CustomException(ErrorCode.CANNOT_VERIFY);
        }

        if (userClient.getStore(token, storeId).getBody() == null) {
            throw new CustomException(ErrorCode.NO_ACCESS);
        }

        reservations.sort(Comparator.comparing(Reservation::getReserveDate));
        reservations.sort(Comparator.comparing(Reservation::getReserveDate)
                .thenComparing(Reservation::getReserveTime));
        return reservations;
    }
}
