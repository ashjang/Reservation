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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    // 고객 - 도착알림
    @Transactional
    public String checkArrived(String userPhone, Long reservationId) {
        Reservation reservation = reservationRepository.findByPhoneAndId(userPhone, reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_CORRECT_RESERVE));

        if (reservation.isUsedRv()) {
            return "이미 도착 확인되었습니다.";
        }

        reservation.setUsedRv(true);
        return "도착 확인되었습니다.";
    }

    // 고객 - 사용완료
    public List<Reservation> getUsedReservation(String phone) {
        List<Reservation> reservations = reservationRepository.findAllByPhoneAndUsedRvIsTrue(phone);
        if (reservations.isEmpty()) {
            throw new CustomException(ErrorCode.CANNOT_VERIFY_RESERVATION);
        }

        return reservations;
    }

    // 고객 - 리뷰완료
    @Transactional
    public boolean setReviewedReservation(Long rvId) {
        Reservation reservation = reservationRepository.findById(rvId)
                .orElseThrow(() -> new CustomException(ErrorCode.CANNOT_VERIFY_RESERVATION));
        reservation.setReviewed(true);
        return true;
    }
}
