package com.ashjang.reservation.controller;

import com.ashjang.domain.token.JwtProvider;
import com.ashjang.domain.token.UserVo;
import com.ashjang.reservation.domain.AddReserveForm;
import com.ashjang.reservation.domain.dto.ReservationDto;
import com.ashjang.reservation.domain.model.Reservation;
import com.ashjang.reservation.exception.CustomException;
import com.ashjang.reservation.exception.ErrorCode;
import com.ashjang.reservation.service.CustomerReservationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/reservation")
public class CustomerReservationController {
    private final CustomerReservationService customerService;
    private final JwtProvider jwtProvider;

    @ApiOperation(value = "고객용 예약", response = ReservationDto.class)
    @PostMapping("/add")
    public ResponseEntity<ReservationDto> addReservation(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                         @RequestBody AddReserveForm reserveForm) {
        UserVo userVo = jwtProvider.getUserVo(token);
        if (!userVo.getUserType().equals("CUSTOMER")) {
            throw new CustomException(ErrorCode.NO_ACCESS_USER);
        }
        return ResponseEntity.ok(
                ReservationDto.from(customerService.isPossible(userVo.getPhone(), reserveForm))
        );
    }

    @ApiOperation(value = "고객 도착 알림", response = String.class)
    @PutMapping
    public ResponseEntity<String> checkArrived(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                               @RequestParam Long reservationId) {
        UserVo userVo = jwtProvider.getUserVo(token);
        if (!userVo.getUserType().equals("CUSTOMER")) {
            throw new CustomException(ErrorCode.NO_ACCESS_USER);
        }

        return ResponseEntity.ok(
                customerService.checkArrived(userVo.getPhone(), reservationId)
        );
    }

    @ApiOperation(value = "고객의 사용한 예약", response = List.class)
    @GetMapping("/use")
    public ResponseEntity<List<ReservationDto>> usedReservation(@RequestHeader(name = "X-AUTH-TOKEN") String token) {
        UserVo userVo = jwtProvider.getUserVo(token);
        if (!userVo.getUserType().equals("CUSTOMER")) {
            throw new CustomException(ErrorCode.NO_ACCESS_USER);
        }

        return ResponseEntity.ok(
                customerService.getUsedReservation(userVo.getPhone()).stream()
                        .map(ReservationDto::from).collect(Collectors.toList())
        );
    }

    @ApiOperation(value = "고객의 리뷰 완료")
    @PutMapping("/reviewed")
    public ResponseEntity<Void> reviewedReservation(@RequestParam Long rvId) {
        customerService.setReviewedReservation(rvId);
        return ResponseEntity.ok().build();
    }
}
