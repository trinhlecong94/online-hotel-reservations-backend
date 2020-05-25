package com.onlinehotelreservations.controller.authentication.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginDTO {

    @NotBlank
    @Pattern(regexp = "^\\S*$", message = "Username can't be white spaces")
    @Size(min = 8, message = "Username must be 8 characters")
    private String username;

    @NotBlank
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message = "Password must be 8 characters including 1 uppercase letter, 1 lowercase letter and numeric characters")
    private String password;
}
