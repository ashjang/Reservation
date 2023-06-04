package com.ashjang.review.controller;

import com.ashjang.domain.token.JwtProvider;
import com.ashjang.domain.token.UserVo;
import com.ashjang.review.domain.AddReviewForm;
import com.ashjang.review.exception.CustomException;
import com.ashjang.review.exception.ErrorCode;
import com.ashjang.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final JwtProvider jwtProvider;

    @PostMapping("/add")
    public ResponseEntity<String> addReview(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                            @RequestParam Long storeId,
                                            @RequestBody AddReviewForm form) {
        UserVo userVo = jwtProvider.getUserVo(token);
        if (!userVo.getUserType().equals("CUSTOMER")) {
            throw new CustomException(ErrorCode.NO_ACCESS_USER);
        }

        if (!reviewService.isUsed(token, storeId)) {
            throw new CustomException(ErrorCode.CANNOT_REVIEW);
        }

        return ResponseEntity.ok(
                reviewService.addReview(userVo.getUserId(), storeId, form)
        );
    }
}
