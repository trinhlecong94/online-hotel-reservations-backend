package com.onlinehotelreservations.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reservation")
public class ReservationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @NotNull
    private UserEntity user;

    private double totalBeforeTax;

    private double tax;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "reservation_promo",
            joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "promo_id", referencedColumnName = "id"))
    private List<PromoEntity> promos;

    public ReservationEntity() {
    }

    public ReservationEntity(int id, UserEntity user, double totalBeforeTax, double tax, List<PromoEntity> promos) {
        this.id = id;
        this.user = user;
        this.totalBeforeTax = totalBeforeTax;
        this.tax = tax;
        this.promos = promos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public double getTotalBeforeTax() {
        return totalBeforeTax;
    }

    public void setTotalBeforeTax(double totalBeforeTax) {
        this.totalBeforeTax = totalBeforeTax;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public List<PromoEntity> getPromos() {
        return promos;
    }

    public void setPromos(List<PromoEntity> promos) {
        this.promos = promos;
    }

}
