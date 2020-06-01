package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, Integer> {
    @Query(
            value = "SELECT * FROM room_type\n" +
                    "where name like CONCAT('%', :valueSearch , '%') ORDER BY capacity ASC",
            nativeQuery = true
    )
    List<RoomTypeEntity> findRoomTypesByKeyword(@Param("valueSearch") String valueSearch);
}

