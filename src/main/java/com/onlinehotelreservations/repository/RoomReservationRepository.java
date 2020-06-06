package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.RoomReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface RoomReservationRepository extends JpaRepository<RoomReservationEntity,Integer> {

    @Query(value = "SELECT r FROM RoomReservationEntity r WHERE r.room.id = ?1")
    RoomReservationEntity findByRoomID(int id);
}
