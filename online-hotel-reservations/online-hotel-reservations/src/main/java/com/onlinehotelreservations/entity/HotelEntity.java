package com.onlinehotelreservations.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "hotel")
public class HotelEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 45, unique = true)
    @NotNull
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<FeedbackEntity> listFeedback;

    @OneToOne
    @NotNull
    private BrandEntity brand;

    public HotelEntity() {
    }

    public HotelEntity(int id, String name, List<FeedbackEntity> listFeedback, BrandEntity brand) {
        this.id = id;
        this.name = name;
        this.listFeedback = listFeedback;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FeedbackEntity> getListFeedback() {
        return listFeedback;
    }

    public void setListFeedback(List<FeedbackEntity> listFeedback) {
        this.listFeedback = listFeedback;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

}
