package com.ashjang.reservation.controller;

import com.ashjang.domain.token.JwtProvider;
import com.ashjang.domain.token.UserVo;
import com.ashjang.reservation.domain.dto.ReservationDto;
import com.ashjang.reservation.exception.CustomException;
import com.ashjang.reservation.exception.ErrorCode;
import com.ashjang.reservation.service.SellerReservationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller/reservation")
public class SellerReservationController {
    private final SellerReservationService sellerReservationService;
    private final JwtProvider jwtProvider;

    @ApiOperation(value = "셀러용 예약확인", response = List.class)
    @GetMapping
    public ResponseEntity<List<ReservationDto>> getReservations(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                                @RequestParam("storeId") Long storeId) {
        UserVo userVo = jwtProvider.getUserVo(token);
        if (!userVo.getUserType().equals("SELLER")) {
            throw new CustomException(ErrorCode.NO_ACCESS_USER);
        }

        return ResponseEntity.ok(
                sellerReservationService.getReservation(storeId, token).stream()
                        .map(ReservationDto::from).collect(Collectors.toList())
        );

    }
}
