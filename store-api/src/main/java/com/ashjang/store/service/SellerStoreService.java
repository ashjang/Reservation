package com.ashjang.store.service;

import com.ashjang.store.domain.form.AddStoreForm;
import com.ashjang.store.domain.form.UpdateStoreForm;
import com.ashjang.store.domain.model.Store;
import com.ashjang.store.domain.repository.StoreRepository;
import com.ashjang.store.exception.CustomException;
import com.ashjang.store.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerStoreService {
    private final StoreRepository storeRepository;

    // 상점 등록
    @Transactional
    public Store addStore(Long sellerId, AddStoreForm addStoreForm) {
        if (storeRepository.findByName(addStoreForm.getName()).isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_EXISTS);
        }

        return storeRepository.save(Store.from(sellerId, addStoreForm));
    }

    // 상점 수정
    @Transactional
    public Store updateStore(Long sellerId, UpdateStoreForm updateStoreForm) {
        Store store = storeRepository.findBySellerIdAndId(sellerId, updateStoreForm.getStoreId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STORE));

        store.setName(updateStoreForm.getName());
        store.setLocation(updateStoreForm.getLocation());
        store.setDescription(updateStoreForm.getDescription());
        store.setPossibleUse(updateStoreForm.isPossibleUse());

        return store;
    }

    // 상품 삭제
    @Transactional
    public void deleteStore(Long sellerId, Long storeId) {
        Store store = storeRepository.findBySellerIdAndId(sellerId, storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STORE));
        storeRepository.delete(store);
    }
}
