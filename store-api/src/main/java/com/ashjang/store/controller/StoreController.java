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

    @ApiOperation(value = "상세 정보 보기", response = List.class)
    @GetMapping("/detail")
    public ResponseEntity<StoreDto> getDetail(@RequestParam Long storeId) {
        return ResponseEntity.ok(
                StoreDto.from(storeService.getDetail(storeId))
        );
    }

    @ApiOperation(value = "이름순으로 정렬된 상점 목록", response = List.class)
    @GetMapping("/byName")
    public ResponseEntity<List<StoreDto>> sortedByName() {
        return ResponseEntity.ok(
                storeService.sortedByName().stream()
                        .map(StoreDto::from).collect(Collectors.toList())
        );
    }

    @ApiOperation(value = "별점 높은 순서대로 정렬된 상점 목록", response = List.class)
    @GetMapping("/byStar")
    public ResponseEntity<List<StoreDto>> sortedByStar() {
        return ResponseEntity.ok(
                storeService.sortedByStar().stream()
                        .map(StoreDto::from).collect(Collectors.toList())
        );
    }

    @ApiOperation(value = "거리 순으로 정렬된 상점 목록", response = List.class)
    @GetMapping("/byDistance")
    public ResponseEntity<List<StoreDto>> sortedByDistance(@RequestParam("myLat") double myLat, @RequestParam("myLng") double myLnt) {
        return ResponseEntity.ok(
                storeService.sortedByDistance(myLat, myLnt).stream()
                        .map(StoreDto::from).collect(Collectors.toList())
        );
    }
}
