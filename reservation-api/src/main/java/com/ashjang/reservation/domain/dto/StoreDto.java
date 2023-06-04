package com.ashjang.reservation.domain.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StoreDto {
    private Long id;
    private String name;
    private String location;
    private String description;
    private Double star;
    private boolean possibleUse;
}
