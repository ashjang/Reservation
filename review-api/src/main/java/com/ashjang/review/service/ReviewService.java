package com.ashjang.review.service;

import com.ashjang.review.client.UserClient;
import com.ashjang.review.domain.AddReviewForm;
import com.ashjang.review.domain.ReservationDto;
import com.ashjang.review.domain.model.Review;
import com.ashjang.review.domain.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserClient userClient;

    // 고객 - 리뷰 작성
    @Transactional
    public String addReview(Long customerId, Long storeId, AddReviewForm form) {
        reviewRepository.save(Review.from(form, customerId, storeId));
        return "리뷰가 작성되었습니다.";
    }

    public boolean isUsed(String token, Long storeId) {
        List<ReservationDto> reservations = userClient.usedReservation(token).getBody();
        for (ReservationDto r : reservations) {
            if (r.isUsedRv() && r.getStoreId() == storeId) {
                return true;
            }
        }
        return false;
    }
}
