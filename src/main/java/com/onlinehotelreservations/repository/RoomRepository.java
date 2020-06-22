package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    @Query(value = "SELECT * FROM room Where room.brand_id = :brandID", nativeQuery = true)
    List<RoomEntity> getAllRoomByBrand(@Param("brandID") int brandID);

    @Query(value = "SELECT * FROM room Where room.brand_id = :brandID and room.room_type_id = :roomType", nativeQuery = true)
    List<RoomEntity> getAllRoomByBrandIdandRoomTypeId(@Param("brandID") int brandID,@Param("roomType") int roomType);

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
    List<RoomEntity> searchRooms(@Param("keyword") String keyword);

}
