package com.onlinehotelreservations.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private int floor;

    @NotNull
    @OneToOne
    private RoomTypeEntity roomType;

    @NotNull
    @OneToOne
    private BrandEntity brand;

    @OneToMany(mappedBy = "room",fetch = FetchType.EAGER)
    private List<RoomReservationEntity> roomReservation;

}
