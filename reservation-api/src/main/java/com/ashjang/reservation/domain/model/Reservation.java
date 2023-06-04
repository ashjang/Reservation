package com.ashjang.reservation.domain.model;

import com.ashjang.reservation.domain.AddReserveForm;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private Long storeId;

    private LocalDate reserveDate;
    private LocalTime reserveTime;
    private boolean usedRv;

    public static Reservation from(AddReserveForm form, String phone) {
        LocalDate date = LocalDate.of(form.getYear(), form.getMonth(), form.getDay());
        LocalTime time = LocalTime.of(form.getHour(), form.getMinute());

        return Reservation.builder()
                .phone(phone)
                .storeId(form.getStoreId())
                .reserveDate(date)
                .reserveTime(time)
                .usedRv(false)
                .build();
    }
}
