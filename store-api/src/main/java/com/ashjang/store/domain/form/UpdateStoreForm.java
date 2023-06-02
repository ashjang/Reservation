package com.ashjang.store.domain.form;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStoreForm {
    private Long id;
    private String name;
    private String location;
    private String description;
}
