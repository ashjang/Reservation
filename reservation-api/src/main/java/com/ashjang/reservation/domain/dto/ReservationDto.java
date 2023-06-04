package com.ashjang.reservation.domain.dto;

import com.ashjang.reservation.domain.model.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDto {
    private Long id;
    private Long storeId;
    private LocalDate reserveDate;
    private LocalTime reserveTime;
    private String phone;
    private boolean usedRv;

    public static ReservationDto from(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .storeId(reservation.getStoreId())
                .reserveDate(reservation.getReserveDate())
                .reserveTime(reservation.getReserveTime())
                .phone(reservation.getPhone())
                .usedRv(reservation.isUsedRv())
                .build();
    }
}
