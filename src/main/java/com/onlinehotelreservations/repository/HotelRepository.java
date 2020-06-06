package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {
    Optional<HotelEntity> findByName(String name);

    @Query(
            value = "SELECT * FROM hotel\n" +
                    "where name like CONCAT('%', :keyword , '%')\n"
            ,
            nativeQuery = true
    )
    List<HotelEntity> findHotelsByKeyword(@Param("keyword") String keyword);
}
