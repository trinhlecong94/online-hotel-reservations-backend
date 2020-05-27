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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
        if (!userRepository.findByEmail(username).isPresent()) {

            Set<RoleEntity> roleIsExists = new HashSet<>();
            for (Role role : roles) {
                roleIsExists.add(roleRepository.findByName(role.toString()));
            }
            userRepository.save(UserEntity.builder().email(username).password(new BCryptPasswordEncoder().encode(password))
                    .roleEntities(roleIsExists)
                    .firstName("khanh")
                    .lastName("nguyen")
                    .phone("0382189922")
                    .birthday(new Date())
                    .build());
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        addRoleIfMissing(Role.ROLE_ADMIN);
        addRoleIfMissing(Role.ROLE_USER);
        addUserIfMissing("khanh1025@gmail.com", "123456789aaA", Role.ROLE_USER);
        addUserIfMissing("khanhadmin1025@gmail.com", "123456789aaA", Role.ROLE_USER, Role.ROLE_ADMIN);

        if (signingKey == null || signingKey.length() == 0) {
            String jws = Jwts.builder()
                    .setSubject("kunlezIsme")
                    .signWith(SignatureAlgorithm.HS256, "kunlezIsmeApi").compact();

            System.out.println("Use this jwt key:");
            System.out.println("jwt-key=" + jws);
        }
    }
}
