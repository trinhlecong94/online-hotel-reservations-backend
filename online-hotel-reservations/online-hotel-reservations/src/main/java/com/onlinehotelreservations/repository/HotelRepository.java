package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {
}
