package com.ashjang.store.service;

import com.ashjang.domain.location.Location;
import com.ashjang.domain.location.LocationProvider;
import com.ashjang.store.domain.model.Store;
import com.ashjang.store.domain.repository.StoreRepository;
import com.ashjang.store.exception.CustomException;
import com.ashjang.store.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final LocationProvider locationProvider;

    @Value("${location.api.key}")
    private String apiKey;

    // 상점 검색
    public List<Store> searchByName(String name) {
        List<Store> storeList = storeRepository.searchByName(name);
        if (storeList.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_STORE);
        }

        return storeList;
    }

    // 상점 정보 보기
    public Store getDetail(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STORE));
    }

    // 상점 목록 - 가나다순
    public List<Store> sortedByName() {
        List<Store> storeList = storeRepository.sortedByName();
        if (storeList.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_STORE);
        }

        return storeList;
    }

    // 상점 목록 - 별점 높은 순서대로
    public List<Store> sortedByStar() {
        List<Store> storeList = storeRepository.sortedByStar();
        if (storeList.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_STORE);
        }

        return storeList;
    }

    // 상점 목록 - 거리순으로
    public List<Store> sortedByDistance(double myLat, double myLng) {
        List<Store> stores = storeRepository.findAll();

        Map<Store, Double> arr = new HashMap<>();

        for (Store store: stores) {
            Location location = locationProvider.getLocation(apiKey, store.getLocation());
            double distance = locationProvider.getDistance(location.getLatitude(), location.getLongitude(), myLat, myLng);
            arr.put(store, distance);

        }

        stores = arr.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return stores;
    }
}