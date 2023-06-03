package com.ashjang.store.domain.repository;

import com.ashjang.store.domain.model.Store;

import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> searchByName(String name);
}
