package com.ashjang.store.domain.repository;

import com.ashjang.store.domain.model.QStore;
import com.ashjang.store.domain.model.Store;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Store> searchByName(String name) {
        String search = "%" + name + "%";

        QStore qStore = QStore.store;
        return queryFactory.selectFrom(qStore)
                .where(qStore.name.like(search))
                .fetch();
    }

    @Override
    public List<Store> sortedByName() {
        QStore qStore = QStore.store;
        return queryFactory.selectFrom(qStore)
                .orderBy(qStore.name.asc())
                .fetch();
    }

    @Override
    public List<Store> sortedByStar() {
        QStore qStore = QStore.store;
        return queryFactory.selectFrom(qStore)
                .orderBy(qStore.star.desc())
                .fetch();
    }
}
