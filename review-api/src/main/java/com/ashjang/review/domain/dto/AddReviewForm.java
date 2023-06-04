package com.ashjang.review.domain.dto;

import lombok.Getter;

@Getter
public class AddReviewForm {
    private Long storeId;
    private Double star;
    private String contents;
}
