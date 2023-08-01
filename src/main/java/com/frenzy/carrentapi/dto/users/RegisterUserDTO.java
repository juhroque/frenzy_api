package com.frenzy.carrentapi.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record RegisterUserDTO(

        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        String email,
        @NotBlank
        String password,
        @NotBlank
        @Pattern(regexp = "\"(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)\"")
        String cpf,
        LocalDateTime dateOfBirth
) {
}
