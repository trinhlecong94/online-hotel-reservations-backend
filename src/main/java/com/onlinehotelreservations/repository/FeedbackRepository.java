package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.BrandEntity;
import com.onlinehotelreservations.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer> {
    Set<FeedbackEntity> findByBrandEntity(BrandEntity brandEntity);
}
