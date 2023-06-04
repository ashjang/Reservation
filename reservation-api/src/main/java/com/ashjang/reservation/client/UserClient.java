package com.ashjang.reservation.client;

import com.ashjang.reservation.domain.dto.StoreDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "store-api", url = "${feign.client.url.store-api}")
public interface UserClient {

    // 예약 가능여부
    @GetMapping("/store/detail")
    ResponseEntity<StoreDto> getDetail(@RequestParam Long storeId);
}
