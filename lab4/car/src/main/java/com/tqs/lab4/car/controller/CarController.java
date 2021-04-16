package com.tqs.lab4.car.controller;

import com.tqs.lab4.car.model.Car;
import com.tqs.lab4.car.services.CarManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarManagerService cms;

    @PostMapping(path = "/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car c = cms.save(car);
        return new ResponseEntity<Car>(c, HttpStatus.CREATED);
    }

    @GetMapping(path = "/cars")
    public List<Car> getAllCars() {
        return cms.getAllCars();
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Long carId) throws ResourceNotFoundException {
        Car car = cms.getCarDetails(carId).orElseThrow(() -> new ResourceNotFoundException("Car not found for id: " + carId));
        return ResponseEntity.ok().body(car);
    }


}
