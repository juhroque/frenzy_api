package com.frenzy.carrentapi.services;

import com.frenzy.carrentapi.dto.cars.RegisterCarDTO;
import com.frenzy.carrentapi.exceptions.BadRequestException;
import com.frenzy.carrentapi.models.cars.Car;
import com.frenzy.carrentapi.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void registerCar(RegisterCarDTO data){
        Boolean existsLicensePlate = carRepository.
                existsByLicensePlate(data.licensePlate());

        if(existsLicensePlate){
            throw new BadRequestException(
                    "License Plate" + data.licensePlate() + "is already registered."
            );
        }

        carRepository.save(new Car(data));
    }

}
