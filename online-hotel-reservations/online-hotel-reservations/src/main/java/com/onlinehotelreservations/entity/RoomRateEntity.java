package com.onlinehotelreservations.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "room_rate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private double rate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @OneToOne
    @NotNull
    private RoomTypeEntity roomType;

}
