package com.onlinehotelreservations.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "email")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String mail;

    @OneToOne
    @NotNull
    private UserEntity user;

    @OneToOne
    @NotNull
    private HotelEntity hotel;

}
