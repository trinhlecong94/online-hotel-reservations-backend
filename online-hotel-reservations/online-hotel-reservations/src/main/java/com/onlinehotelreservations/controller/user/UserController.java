package com.onlinehotelreservations.controller.user;

import com.onlinehotelreservations.controller.user.DTO.UserDTO;
import com.onlinehotelreservations.service.UserService;
import com.onlinehotelreservations.shared.model.ApiData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @GetMapping("/{id}")
    public ApiData<UserDTO> getUserFollowId(@PathVariable("id") int id) {
        return new ApiData<>(this.userMapper.toUserDTO(this.userService.getUserFollowId(id)));
    }

    @GetMapping
    public ApiData<List<UserDTO>> getAllUser() {
        return new ApiData<>(this.userMapper.toUserDTOs(this.userService.getAllUser()));
    }
}
