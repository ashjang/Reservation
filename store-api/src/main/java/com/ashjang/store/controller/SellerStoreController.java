package com.ashjang.store.controller;

import com.ashjang.domain.token.JwtProvider;
import com.ashjang.domain.token.UserVo;
import com.ashjang.store.domain.form.AddStoreForm;
import com.ashjang.store.domain.dto.StoreDto;
import com.ashjang.store.domain.form.UpdateStoreForm;
import com.ashjang.store.exception.CustomException;
import com.ashjang.store.exception.ErrorCode;
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
    @PostMapping("/add")
    public ResponseEntity<StoreDto> addStore(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                             @RequestBody AddStoreForm addStoreForm) {
        UserVo userVo = jwtProvider.getUserVo(token);
        if (!userVo.getUserType().equals("SELLER")) {
            throw new CustomException(ErrorCode.NO_ACCESS_USER);
        }

        return ResponseEntity.ok(
                StoreDto.from(sellerStoreService.addStore(userVo.getUserId(), addStoreForm))
        );
    }

    @ApiOperation(value = "상점 수정", response = StoreDto.class)
    @PutMapping("/update")
    public ResponseEntity<StoreDto> updateStore(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                                @RequestBody UpdateStoreForm updateStoreForm) {
        UserVo userVo = jwtProvider.getUserVo(token);

        return ResponseEntity.ok(
                StoreDto.from(sellerStoreService.updateStore(userVo.getUserId(), updateStoreForm))
        );
    }

    @ApiOperation(value = "상점 삭제", response = String.class)
    @DeleteMapping
    public ResponseEntity<Void> deleteStore(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                            @RequestParam Long storeId) {
        UserVo userVo = jwtProvider.getUserVo(token);
        if (!userVo.getUserType().equals("SELLER")) {
            throw new CustomException(ErrorCode.NO_ACCESS_USER);
        }

        sellerStoreService.deleteStore(userVo.getUserId(), storeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<StoreDto> getStore(@RequestHeader(name = "X-AUTH-TOKEN") String token,
                                             @RequestParam Long storeId) {
        UserVo userVo = jwtProvider.getUserVo(token);
        if (!userVo.getUserType().equals("SELLER")) {
            throw new CustomException(ErrorCode.NO_ACCESS_USER);
        }

        return ResponseEntity.ok(
                StoreDto.from(sellerStoreService.getStore(userVo.getUserId(), storeId))
        );
    }
}
