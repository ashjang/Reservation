package com.ashjang.store.service;

import com.ashjang.store.domain.AddStoreForm;
import com.ashjang.store.domain.model.Store;
import com.ashjang.store.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerStoreService {
    private final StoreRepository storeRepository;

    // 상품 등록
    @Transactional
    public Store addStore(Long sellerId, AddStoreForm addStoreForm) {
        return storeRepository.save(Store.from(sellerId, addStoreForm));

    }
}
