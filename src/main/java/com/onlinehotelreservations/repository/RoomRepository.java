package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    @Query(value = "SELECT * FROM room_reservation where room_id=:id and start_date+1<now() and (end_date+1>now() or end_date is NULL)", nativeQuery = true)
    RoomEntity getRoomAvailable(@Param("id") int id);

    @Query(value = "SELECT * FROM room Where room.brand_id = :brandID", nativeQuery = true)
    List<RoomEntity> getAllRoomByBrand(@Param("brandID") int brandID);

    @Query(value = "SELECT room.id as id,\n" +
            "room.floor as floor,\n" +
            "room.name as name, \n" +
            "room.brand_id as brand_id,\n" +
            "room.room_type_id as room_type_id\n" +
            "FROM room  \n" +
            "INNER JOIN room_type ON room.room_type_id = room_type.id\n" +
            "INNER JOIN brand ON room.brand_id = brand.id\n" +
            "WHERE LOWER(room.name) like LOWER(CONCAT('%', :keyword ,'%'))\n" +
            "OR LOWER(brand.name) like LOWER(CONCAT('%', :keyword ,'%'))\n" +
            "OR LOWER(room_type.name) like LOWER(CONCAT('%', :keyword ,'%'))\n" +
            "OR LOWER(brand.address) like LOWER(CONCAT('%', :keyword ,'%'))"
            , nativeQuery = true)
    List<RoomEntity> searchRooms(@Param("keyword")String keyword);


    @Query(value = "SELECT room.id as id,\n" +
            "room.floor as floor,\n" +
            "room.name as name,\n" +
            "room.brand_id as brand_id,\n" +
            "room.room_type_id as room_type_id\n" +
            "FROM room \n" +
            "LEFT JOIN room_reservation \n" +
            "ON room.id = room_reservation.room_id\n" +
            "WHERE (((room_reservation.end_date<=:startDate) OR (room_reservation.start_date>=:endDate))\n" +
            "AND room_reservation.end_date IS NOT NULL\n" +
            "OR room_reservation.start_date IS NULL)\n" +
            "AND room.brand_id =:brandId\n" +
            "AND room.room_type_id=:roomTypeId\n" +
            "ORDER BY room.floor", nativeQuery = true)
    List<RoomEntity> getAllRoomAvailableByBandIdAndRoomTypeId(@Param("startDate") Date startDate, @Param("endDate") Date endDate,@Param("brandId") int brandId,@Param("roomTypeId") int roomTypeId);
}
