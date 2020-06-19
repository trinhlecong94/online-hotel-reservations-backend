package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {

    @Query(value = "SELECT r FROM ReservationEntity r WHERE r.user.id=?1")
    List<ReservationEntity> getReservationUserId(int id);
}
