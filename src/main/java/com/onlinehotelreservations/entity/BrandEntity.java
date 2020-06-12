package com.onlinehotelreservations.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "brand")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 45, unique = true)
    @NotNull
    private String name;

    private String imgLink;

    private String address;

    private  int floor;

    @ManyToOne
    @JoinColumn
    private HotelEntity hotelEntity;

    @OneToMany(mappedBy = "brandEntity")
    private List<FeedbackEntity> feedbackEntities;
}
