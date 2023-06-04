package com.ashjang.reservation.service;

import com.ashjang.reservation.client.UserClient;
import com.ashjang.reservation.domain.AddReserveForm;
import com.ashjang.reservation.domain.dto.StoreDto;
import com.ashjang.reservation.domain.model.Reservation;
import com.ashjang.reservation.domain.repository.ReservationRepository;
import com.ashjang.reservation.exception.CustomException;
import com.ashjang.reservation.exception.ErrorCode;
import lombok.RequiredArgsConstructor;;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerReservationService {
    private final ReservationRepository reservationRepository;
    private final UserClient userClient;

    // 고객 - 예약
    public Reservation isPossible(String userPhone, AddReserveForm reserveForm) {
        StoreDto storeDto = userClient.getDetail(reserveForm.getStoreId()).getBody();
        if (!storeDto.isPossibleUse()) {
            throw new CustomException(ErrorCode.CANNOT_RESERVE);
        }

        return reservationRepository.save(Reservation.from(reserveForm, userPhone));
    }

}
