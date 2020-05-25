package com.onlinehotelreservations.config;

import com.onlinehotelreservations.entity.RoleEntity;
import com.onlinehotelreservations.entity.UserEntity;
import com.onlinehotelreservations.repository.RoleRepository;
import com.onlinehotelreservations.repository.UserRepository;
import com.onlinehotelreservations.shared.enums.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Configuration
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Value("${jwt-key}")
    private String signingKey;

    private void addRoleIfMissing(Role role) {
        if (roleRepository.findByName(role.toString()) == null) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(role.toString());
            roleRepository.save(roleEntity);
        }
    }

    private void addUserIfMissing(String username, String password, Role... roles) {
        if (userRepository.findByUserName(username) == null) {

            List<RoleEntity> roleIsExists = new ArrayList<>();
            for (Role role : roles) {
                roleIsExists.add(roleRepository.findByName(role.toString()));
            }

            userRepository.save(UserEntity.builder().userName(username).password(new BCryptPasswordEncoder().encode(password))
                    .accountRoles(roleIsExists).firstName("khanh").lastName("nguyen").phone("0382189922").build());
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        addRoleIfMissing(Role.ROLE_ADMIN);
        addRoleIfMissing(Role.ROLE_USER);

        addUserIfMissing("user10251025", "123456789aaA", Role.ROLE_USER);
        addUserIfMissing("admin10251025", "123456789aaA", Role.ROLE_USER, Role.ROLE_ADMIN);

        if (signingKey == null || signingKey.length() == 0) {
            String jws = Jwts.builder()
                    .setSubject("kunlezIsme")
                    .signWith(SignatureAlgorithm.HS256, "kunlezIsmeApi").compact();

            System.out.println("Use this jwt key:");
            System.out.println("jwt-key=" + jws);
        }
    }
}
