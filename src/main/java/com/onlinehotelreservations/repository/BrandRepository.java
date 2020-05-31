package com.onlinehotelreservations.repository;


import com.onlinehotelreservations.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {
    Optional<BrandEntity> findByName(String name);

    @Query(
            value = "SELECT * FROM brand\n" +
                    "where name like CONCAT('%', :keyword , '%')\n" +
                    "or imgLink like CONCAT('%', :keyword ,'%')\n" +
                    "or address like CONCAT('%', :keyword ,'%')\n"
            ,
            nativeQuery = true
    )
    List<BrandEntity> findBrandsByKeyword(@Param("keyword") String keyword);
}
