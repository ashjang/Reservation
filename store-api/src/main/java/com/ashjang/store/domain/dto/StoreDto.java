package com.ashjang.store.domain.dto;

import com.ashjang.store.domain.model.Store;
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

    public static StoreDto from(Store store) {
        return StoreDto.builder()
                .id(store.getId())
                .name(store.getName())
                .location(store.getLocation())
                .description(store.getDescription())
                .star(store.getStar())
                .build();
    }
}
