package com.frenzy.carrentapi.models.cars;

import com.frenzy.carrentapi.dto.cars.*;
import com.frenzy.carrentapi.models.users.User;
import com.frenzy.carrentapi.repositories.UserRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;


@Entity(name="Car")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name="cars")
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="host_id")
    private User host;

    @Column(name="brand")
    private String brand;

    @Column(name="model")
    private String model;

    @Column(name="description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private Category category;
    private String color;
    private int seats;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name="review")
    private double review;

    @Enumerated(EnumType.STRING)
    @Column(name="state")
    private State state;

    @Column(name="daily_price")
    private BigDecimal dailyPrice;

    @Column(name="lat")
    private String lat;

    @Column(name="lng")
    private String lng;

    private boolean active;

    public Car(RegisterCarDTO data, User host){
        this.host = host;
        this.brand = data.brand();
        this.model = data.model();
        this.description = data.description();
        this.category = data.category();
        this.color = data.color();
        this.review = 0;
        this.seats = data.seats();
        this.licensePlate = data.licensePlate();
        this.dailyPrice = data.dailyPrice();
        this.lat = data.lat();
        this.lng = data.lng();
        this.active = true;
    }

}
