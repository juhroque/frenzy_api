package com.frenzy.carrentapi.repositories;

import com.frenzy.carrentapi.dto.users.RegisterUserDTO;
import com.frenzy.carrentapi.models.users.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldCheckIfUserExistsByCpf() {
        //given
        String cpf = "15769400633";
        RegisterUserDTO userDTO = new RegisterUserDTO(
          "Julia",
          "Roque",
          "julia@gmail.com",
          "random",
          cpf,
          LocalDateTime.of(2005, 6, 2, 8, 45)
        );

        //when
        User user = new User(userDTO);
        underTest.save(user);

        //then
        boolean expected = underTest.existsByCpf(cpf);
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldFindUserByEmail(){
        //given
        String email = "julia@gmail.com";
        RegisterUserDTO userDTO = new RegisterUserDTO(
                "Julia",
                "Roque",
                email,
                "random",
                "15769400633",
                LocalDateTime.of(2005, 6, 2, 8, 45)
        );

        User user = new User(userDTO);
        underTest.save(user);

        UserDetails expected = underTest.findByEmail(email);
        assertThat(expected).isEqualTo(user);
    }
}