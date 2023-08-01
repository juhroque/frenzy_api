package com.frenzy.carrentapi.repositories;

import com.frenzy.carrentapi.dto.cars.*;
import com.frenzy.carrentapi.dto.users.RegisterUserDTO;
import com.frenzy.carrentapi.models.cars.Car;
import com.frenzy.carrentapi.models.cars.Category;
import com.frenzy.carrentapi.models.users.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository underTest;

    @Autowired
    private UserRepository userRepository;

    @Test
    void  itShouldCheckIfLicensePlateExists(){
        // given
        String licensePlate = "BRAGS19";

        RegisterUserDTO userDTO = new RegisterUserDTO(
                "Julia",
                "Roque",
                "julia@gmail.com",
                "random",
                "15769400633",
                LocalDateTime.of(2005, 6, 2, 8, 45)
        );

        //when
        User user = new User(userDTO);

        userRepository.save(user);


        RegisterCarDTO carDTO = new RegisterCarDTO(
                user.getId(),
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


        Car car = new Car(carDTO, user);
        underTest.save(car);

        // when
        boolean expected = underTest.existsByLicensePlate(licensePlate);

        // then
        assertThat(expected).isTrue();

    }
}