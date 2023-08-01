package com.frenzy.carrentapi.services;

import com.frenzy.carrentapi.dto.cars.RegisterCarDTO;
import com.frenzy.carrentapi.exceptions.BadRequestException;
import com.frenzy.carrentapi.models.cars.Car;
import com.frenzy.carrentapi.repositories.CarRepository;
import com.frenzy.carrentapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public void registerCar(RegisterCarDTO data){
        Boolean existsLicensePlate = carRepository.
                existsByLicensePlate(data.licensePlate());

        if(existsLicensePlate){
            throw new BadRequestException(
                    "License Plate" + data.licensePlate() + "is already registered."
            );
        }

        carRepository.save(new Car(data, userRepository.getReferenceById(data.hostId())));
    }

}
