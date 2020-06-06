package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
public interface RoomRepository extends JpaRepository<RoomEntity,Integer> {
    @Query(value = "SELECT * FROM room Where room.hotel_id = :hotelID", nativeQuery = true)
    List<RoomEntity> getAllRoomByHotelID(@Param("hotelID")int hotelID);
}
