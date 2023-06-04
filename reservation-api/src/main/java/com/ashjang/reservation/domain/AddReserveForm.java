package com.ashjang.reservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddReserveForm {
    private Long storeId;

    private int year;
    private int month;
    private int day;

    private int hour;
    private int minute;
}
