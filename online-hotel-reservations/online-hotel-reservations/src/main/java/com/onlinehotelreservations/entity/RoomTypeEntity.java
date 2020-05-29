package com.onlinehotelreservations.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "room_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomTypeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private double price;

    @NotNull
    private String type;

    @NotNull
    private double size;

    @NotNull
    private double capacity;

    @NotNull
    private double description;

    @OneToMany(cascade=CascadeType.ALL)
    private Set<ImageEntity> images;

    @OneToMany(cascade=CascadeType.ALL)
    private Set<ExtraEntity> extras;

}

//    @ElementCollection
//    @CollectionTable(name = "extras", joinColumns = @JoinColumn(name = "id"))
//    @Column(name = "extras")
//    private Set<String> extras;
