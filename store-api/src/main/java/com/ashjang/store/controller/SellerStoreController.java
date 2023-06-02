package com.ashjang.store.controller;

import com.ashjang.domain.exception.CustomException;
import com.ashjang.domain.exception.ErrorCode;
import com.ashjang.domain.token.JwtProvider;
import com.ashjang.domain.token.UserVo;
import com.ashjang.store.domain.AddStoreForm;
import com.ashjang.store.domain.dto.StoreDto;
import com.ashjang.store.service.SellerStoreService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller/store")
@RequiredArgsConstructor
public class SellerStoreController {
    private final SellerStoreService sellerStoreService;
    private final JwtProvider jwtProvider;

    @ApiOperation(value = "상점 추가", response = StoreDto.class)
    @PostMapping
    public ResponseEntity<StoreDto> addStore(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                             @RequestBody AddStoreForm addStoreForm) {
        UserVo userVo = jwtProvider.getUserVo(token);
        if (!userVo.getUserType().equals("SELLER")) {
            throw new CustomException(ErrorCode.NO_ACCESS_USER);
        }

        return ResponseEntity.ok(
                StoreDto.from(sellerStoreService.addStore(userVo.getId(), addStoreForm))
        );
    }
}
