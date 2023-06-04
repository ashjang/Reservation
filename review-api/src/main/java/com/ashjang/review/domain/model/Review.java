package com.ashjang.review.domain.model;

import com.ashjang.review.domain.AddReviewForm;
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
    private String title;
    private String contents;

    public static Review from(AddReviewForm addReviewForm, Long customerId, Long storeId) {
        return Review.builder()
                .customerId(customerId)
                .storeId(storeId)
                .title(addReviewForm.getTitle())
                .contents(addReviewForm.getContents())
                .build();
    }
}
