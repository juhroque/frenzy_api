package com.frenzy.carrentapi.services;

import com.frenzy.carrentapi.dto.cars.RegisterCarDTO;
import com.frenzy.carrentapi.exceptions.BadRequestException;
import com.frenzy.carrentapi.models.cars.Car;
import com.frenzy.carrentapi.models.cars.Category;
import com.frenzy.carrentapi.repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;
    private CarService underTest;

    @BeforeEach
    void setUp(){
        underTest = new CarService(carRepository);
    }

    @Test
    void canRegisterCar(){

        RegisterCarDTO carDTO = new RegisterCarDTO(
                1L,
                "bmw",
                "x5",
                null,
                Category.CAR,
                null,
                2,
                "BRAGS19",
                BigDecimal.valueOf(12.50),
                "12.9823",
                "13.293870"
        );

        underTest.registerCar(carDTO);

        ArgumentCaptor<Car> carArgumentCaptor =
                ArgumentCaptor.forClass(Car.class);

        verify(carRepository)
                .save(carArgumentCaptor.capture());

        Car capturedCar = carArgumentCaptor.getValue();

        assertThat(capturedCar).isEqualTo(new Car(carDTO));
    }

    @Test
    void willThrowWhenLicensePlateExists(){
        RegisterCarDTO carDTO = new RegisterCarDTO(
                1L,
                "bmw",
                "x5",
                null,
                Category.CAR,
                null,
                2,
                "BRAGS19",
                BigDecimal.valueOf(12.50),
                "12.9823",
                "13.293870"
        );

        given(carRepository.existsByLicensePlate(carDTO.licensePlate()))
                .willReturn(true);

        assertThatThrownBy(() -> underTest.registerCar(carDTO))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(
                        "License Plate" + carDTO.licensePlate() + "is already registered.");
    }
}