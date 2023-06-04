package com.ashjang.review.domain.dto;

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
    private boolean reviewed;
}
