package com.tqs.lab4.car.services;

import com.tqs.lab4.car.model.Car;
import com.tqs.lab4.car.repository.CarRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {

    @Autowired
    private CarRepository repo;

    public Car save(Car car) {
        return repo.save(car);
    }

    public List<Car> getAllCars() {
        return repo.findAll();
    }

    public Optional<Car> getCarDetails(Long id) {
        return repo.findByCarId(id);
    }
}
