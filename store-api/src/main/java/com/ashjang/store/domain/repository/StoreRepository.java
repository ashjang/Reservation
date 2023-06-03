package com.ashjang.store.domain.repository;

import com.ashjang.store.domain.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom{
    Optional<Store> findByName(String name);

    Optional<Store> findBySellerIdAndId(Long sellerId, Long id);
}
