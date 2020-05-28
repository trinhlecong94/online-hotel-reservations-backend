package com.onlinehotelreservations.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @ManyToOne
    @JoinColumn
    private HotelEntity hotelEntity;

    @OneToMany(mappedBy = "brandEntity", cascade = CascadeType.ALL)
    private List<FeedbackEntity> feedbackEntities;
}
