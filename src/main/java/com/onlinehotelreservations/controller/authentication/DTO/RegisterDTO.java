package com.onlinehotelreservations.controller.authentication.DTO;

import com.onlinehotelreservations.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDTO {

    @NotNull
    @Pattern(regexp = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", message = "Email not match")
    private String email;

    private Set<RoleEntity> roleEntities;

    @NotBlank(message = "password can't be blank")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message = "Password must be 8 characters including 1 uppercase letter, 1 lowercase letter and numeric characters")
    @NotNull
    private String password;

    @NotBlank(message = "Fist name is required")
    @NotNull
    private String firstName;

    @NotBlank(message = "Last name is required")
    @NotNull
    private String lastName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthday;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "(0)+([0-9]{9})\\b", message = "Phone not in correct format")
    private String phone;
}
