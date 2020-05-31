package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, Integer> {

}
