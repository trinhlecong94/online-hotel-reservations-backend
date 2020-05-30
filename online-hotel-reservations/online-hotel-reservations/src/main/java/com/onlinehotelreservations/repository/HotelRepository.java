package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {
    Optional<HotelEntity> findByName(String name);
}
