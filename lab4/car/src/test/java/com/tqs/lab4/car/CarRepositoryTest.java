package com.tqs.lab4.car;

import com.tqs.lab4.car.model.Car;

import com.tqs.lab4.car.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepo;

    @Test
    public void whenFindCar_thenReturnCar() {
        Car c1 = new Car("Mercedes", "Class A");
        entityManager.persistAndFlush(c1);

        Optional<Car> queryResult = carRepo.findByCarId(c1.getCarId());
        assertThat(queryResult).isEqualTo(c1);
    }

    @Test
    public void whenInvalidCar_thenReturnNull() {
        Optional<Car> nonExistingCar = carRepo.findByCarId(111L);
        assertThat(nonExistingCar).isNull();
    }

    @Test
    public void whenFindAllCars_thenHasAllCars() {
        Car c1 = new Car("Mercedes", "Class A");
        entityManager.persist(c1);

        Car c2 = new Car("Mercedes", "Class B");
        entityManager.persist(c2);

        Car c3 = new Car("Mercedes", "Class C");
        entityManager.persist(c3);

        entityManager.flush();

        List<Car> cars = carRepo.findAll();
        assertThat(cars).hasSize(3).extracting(Car::getModel)
                .containsOnly(c1.getModel(), c2.getModel(), c3.getModel());
    }


}