package com.frenzy.carrentapi.repositories;

import com.frenzy.carrentapi.models.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT EXISTS (SELECT 1 FROM Car c WHERE c.licensePlate = :licensePlate)")
    Boolean existsByLicensePlate(String licensePlate);
}
