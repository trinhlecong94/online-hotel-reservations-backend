package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.PromoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface PromoRepository extends JpaRepository<PromoEntity, Integer> {
    @Query(value = "SELECT * FROM promo WHERE start_Date <= NOW() and end_Date >= NOW()",
            nativeQuery = true)
    List<PromoEntity> getAllPromoStillActive();
}
