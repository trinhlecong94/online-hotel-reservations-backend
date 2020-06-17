package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
}
