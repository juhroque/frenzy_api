package com.frenzy.carrentapi.repositories;

import com.frenzy.carrentapi.dto.cars.*;
import com.frenzy.carrentapi.models.cars.Car;
import com.frenzy.carrentapi.models.cars.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository underTest;

    @Test
    void  itShouldCheckIfLicensePlateExists(){
        // given
        String licensePlate = "BRAGS19";

        RegisterCarDTO carDTO = new RegisterCarDTO(
                1L,
                "bmw",
                "x5",
                null,
                Category.CAR,
                null,
                2,
                licensePlate,
                BigDecimal.valueOf(12.50),
                "12.9823",
                "13.293870"
        );
        Car car = new Car(carDTO);
        underTest.save(car);

        // when
        boolean expected = underTest.existsByLicensePlate(licensePlate);

        // then
        assertThat(expected).isTrue();

    }
}