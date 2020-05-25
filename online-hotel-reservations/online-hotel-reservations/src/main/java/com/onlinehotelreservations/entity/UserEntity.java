package com.onlinehotelreservations.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.onlinehotelreservations.shared.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username can't be blank")
    @Pattern(regexp = "^\\S*$", message = "Username can't be white spaces")
    @Size(min = 8, message = "Username must be 8 characters")
    private String userName;

    @Column(nullable = false)
    @NotBlank(message = "password can't be blank")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message = "Password must be 8 characters including 1 uppercase letter, 1 lowercase letter and numeric characters")
    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roleEntities;

    @NotBlank(message = "Fist name is required")
    @NotNull
    private String firstName;

    @NotBlank(message = "Last name is required")
    @NotNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "tel_number")
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "(0)+([0-9]{9})\\b", message = "Phone not in correct format")
    private String phone;

}
