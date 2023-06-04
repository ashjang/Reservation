package com.ashjang.review.client;

import com.ashjang.review.domain.ReservationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "reservation-api", url = "${feign.client.url.reservation-api}")
public interface UserClient {

    @GetMapping("/use")
    ResponseEntity<List<ReservationDto>> usedReservation(@RequestHeader(name = "X-AUTH-TOKEN") String token);
}
