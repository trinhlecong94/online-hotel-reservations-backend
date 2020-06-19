package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.RoomReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RoomReservationRepository extends JpaRepository<RoomReservationEntity,Integer> {

    @Query(value = "SELECT r FROM RoomReservationEntity r WHERE r.room.id = ?1")
    List<RoomReservationEntity> getAllRoomReservationByRoomId(int id);

    @Query(value = "SELECT rs FROM RoomReservationEntity rs WHERE rs.room.brand.id = ?1")
    List<RoomReservationEntity> getRoomReservationByBrandId(int id);
}
