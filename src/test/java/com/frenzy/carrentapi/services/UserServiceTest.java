package com.frenzy.carrentapi.services;

import com.frenzy.carrentapi.dto.users.RegisterUserDTO;
import com.frenzy.carrentapi.exceptions.BadRequestException;
import com.frenzy.carrentapi.models.cars.Car;
import com.frenzy.carrentapi.models.users.User;
import com.frenzy.carrentapi.repositories.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.webservices.server.AutoConfigureMockWebServiceClient;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DataJpaTest
class UserServiceTest {

    private UserService underTest;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        underTest = new UserService(userRepository);
    }

    @Test
    void canRegisterUser() {
        RegisterUserDTO userDTO = new RegisterUserDTO(
                "Julia",
                "Roque",
                "julia@gmail.com",
                "random",
                "15769400633",
                LocalDateTime.of(2005, 6, 2, 8, 45)
        );

        underTest.registerUser(userDTO);

        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository)
                .save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(new User(userDTO));
    }

    @Test
    void willThrowWhenCpfRegistered(){

        String cpf = "15769400633";
        RegisterUserDTO userDTO = new RegisterUserDTO(
                "Julia",
                "Roque",
                "julia@gmail.com",
                "random",
                cpf,
                LocalDateTime.of(2005, 6, 2, 8, 45)
        );

        given(userRepository.existsByCpf(cpf))
                .willReturn(true);

        assertThatThrownBy(() -> underTest.registerUser(userDTO))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining(
                        "This CPF was already registered by an user."
                );
    }
}