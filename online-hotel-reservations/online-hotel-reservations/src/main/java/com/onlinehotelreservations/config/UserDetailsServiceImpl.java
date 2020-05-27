package com.onlinehotelreservations.config;

import com.onlinehotelreservations.controller.authentication.exception.EmailLoginFailedException;
import com.onlinehotelreservations.entity.RoleEntity;
import com.onlinehotelreservations.entity.UserEntity;
import com.onlinehotelreservations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> userOpt = userRepository.findByEmail(username);

        if (userOpt.isPresent()) {

            UserEntity emailEntity = userOpt.get();

            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

            Set<RoleEntity> roles = emailEntity.getRoleEntities();

            for (RoleEntity role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            }

            return new org.springframework.security.core.userdetails.User(
                    emailEntity.getEmail(), emailEntity.getPassword(), grantedAuthorities);

        }

        throw new UsernameNotFoundException("User not found");
    }
}
