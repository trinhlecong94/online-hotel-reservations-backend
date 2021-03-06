package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.HotelEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<HotelEntity, Integer> {
    Optional<HotelEntity> findByName(String name);

    @Query(
            value = "SELECT * FROM hotel\n" +
                    "where name like CONCAT('%', :valueSearch , '%')\n"
            ,
            nativeQuery = true
    )
    List<HotelEntity> paginationHotelsFollowValueSearch(@Param("valueSearch") String valueSearch, Pageable pageable);

    @Query(
            value = "SELECT * FROM hotel\n" +
                    "where name like CONCAT('%', :valueSearch , '%')\n"
            ,
            nativeQuery = true
    )
    List<HotelEntity> findAllHotelFollowValueSearch(@Param("valueSearch") String valueSearch);
}
