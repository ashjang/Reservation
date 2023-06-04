package com.ashjang.review.domain.model;

import com.ashjang.review.domain.dto.AddReviewForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AuditOverride(forClass = BaseEntity.class)
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Long storeId;
    private Double star;
    private String contents;

    public static Review from(AddReviewForm addReviewForm, Long customerId) {
        return Review.builder()
                .customerId(customerId)
                .storeId(addReviewForm.getStoreId())
                .star(addReviewForm.getStar())
                .contents(addReviewForm.getContents())
                .build();
    }
}
