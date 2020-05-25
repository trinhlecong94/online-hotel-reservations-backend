package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.RoleEntity;
import com.onlinehotelreservations.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
    RoleEntity findByName(String name);
}
