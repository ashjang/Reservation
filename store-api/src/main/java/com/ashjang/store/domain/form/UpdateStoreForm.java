package com.ashjang.store.domain.form;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStoreForm {
    private Long storeId;
    private String name;
    private String location;
    private String description;
    private boolean possibleUse;
}
