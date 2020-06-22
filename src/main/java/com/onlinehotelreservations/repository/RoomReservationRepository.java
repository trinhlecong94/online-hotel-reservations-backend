package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.ReservationEntity;
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

    RoomReservationEntity getRoomReservationByReservation(ReservationEntity reservation);

    @Query(value = "SELECT rs FROM RoomReservationEntity rs WHERE rs.reservation.user.email = ?1")
    List<RoomReservationEntity> getRoomReservationByCurrentUserId(String currentUsedId);
}
