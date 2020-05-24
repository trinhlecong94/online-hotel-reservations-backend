package com.onlinehotelreservations.repository;

import com.onlinehotelreservations.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findUserByUsernameAndPassword(String username, String password);

    UserEntity findUserByUsername(String username);

}
