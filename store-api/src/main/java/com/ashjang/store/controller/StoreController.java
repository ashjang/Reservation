package com.ashjang.store.controller;

import com.ashjang.store.domain.dto.StoreDto;
import com.ashjang.store.service.StoreService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    @ApiOperation(value = "이름으로 검색", response = List.class)
    @GetMapping
    public ResponseEntity<List<StoreDto>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(
                storeService.searchByName(name).stream()
                        .map(StoreDto::from).collect(Collectors.toList())
        );
    }
}
