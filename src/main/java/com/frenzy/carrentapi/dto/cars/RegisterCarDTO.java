package com.frenzy.carrentapi.dto.cars;

import com.frenzy.carrentapi.models.cars.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record RegisterCarDTO(

        @NotBlank
        Long hostId,


        String brand,
        String model,
        String description,
        Category category,
        String color,
        int seats,

        @NotBlank
        String licensePlate,

        @NotBlank
        BigDecimal dailyPrice,

        String lat,
        String lng
) {
}
