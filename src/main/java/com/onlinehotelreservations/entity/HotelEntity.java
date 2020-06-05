package com.onlinehotelreservations.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "hotel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 45, unique = true)
    @NotNull
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @OneToMany(mappedBy = "hotelEntity")
    private List<BrandEntity> brandEntities;

    @OneToMany(cascade=CascadeType.ALL)
    private Set<ImageEntity> images;
}
