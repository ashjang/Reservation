package com.ashjang.store.service;

import com.ashjang.store.domain.model.Store;
import com.ashjang.store.domain.repository.StoreRepository;
import com.ashjang.store.exception.CustomException;
import com.ashjang.store.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

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
}