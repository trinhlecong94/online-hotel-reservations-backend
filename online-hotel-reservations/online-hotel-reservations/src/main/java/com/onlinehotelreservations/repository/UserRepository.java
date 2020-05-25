package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUserName(String userName);
}
