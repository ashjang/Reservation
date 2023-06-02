package com.ashjang.store.domain.model;

import com.ashjang.store.domain.AddStoreForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String description;

    private Long sellerId;

    public static Store from(Long sellerId, AddStoreForm addStoreForm) {
        return Store.builder()
                .name(addStoreForm.getName())
                .location(addStoreForm.getLocation())
                .description(addStoreForm.getDescription())
                .sellerId(sellerId)
                .build();
    }
}
