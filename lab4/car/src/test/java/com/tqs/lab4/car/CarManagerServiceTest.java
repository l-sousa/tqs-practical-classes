package com.tqs.lab4.car;

import com.tqs.lab4.car.controller.ResourceNotFoundException;
import com.tqs.lab4.car.model.Car;
import com.tqs.lab4.car.repository.CarRepository;
import com.tqs.lab4.car.services.CarManagerService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepo;

    @InjectMocks
    private CarManagerService cms;

    @BeforeEach
    public void setUp() {
        Car c1 = new Car("Mercedes", "Class A");
        c1.setCarId(111L);

        Car c2 = new Car("Mercedes", "Class B");
        c2.setCarId(222L);

        Car c3 = new Car("Mercedes", "Class C");
        c3.setCarId(333L);

        List<Car> cars = Arrays.asList(c1, c2, c3);

        Mockito.when(carRepo.findAll()).thenReturn(cars);

        Mockito.when(carRepo.findByCarId(c1.getCarId())).thenReturn(Optional.of(c1));
        Mockito.when(carRepo.findByCarId(c2.getCarId())).thenReturn(Optional.of(c2));
        Mockito.when(carRepo.findByCarId(c3.getCarId())).thenReturn(Optional.of(c3));

        Mockito.when(carRepo.findByCarId(444L)).thenReturn(Optional.empty());
    }

    @Test
    public void testCarValidId() throws ResourceNotFoundException {
        Car c1 = cms.getCarDetails(111L).orElseThrow(() -> new ResourceNotFoundException("Car not found! ID: " + 111L));
        assertThat(c1.getModel()).isEqualTo("Class A");

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void testCarInvalidId() throws ResourceNotFoundException {
        assertThatThrownBy(() ->
        {
            cms.getCarDetails(444L).orElseThrow(() -> new ResourceNotFoundException("Car not found! ID: " + 444L));
        }).isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Car not found! ID: " + 444L);

        verifyFindByIdIsCalledOnce();
    }

    @Test
    public void testAllCars() {
        Car c1 = new Car("Mercedes", "Class A");
        c1.setCarId(111L);

        Car c2 = new Car("Mercedes", "Class B");
        c2.setCarId(222L);

        Car c3 = new Car("Mercedes", "Class C");
        c3.setCarId(333L);

        List<Car> cars = cms.getAllCars();

        assertThat(cars).hasSize(3).extracting(Car::getModel).contains(c1.getModel(), c2.getModel(), c3.getModel());

        verifyFindAllCarsIsCalledOnce();

    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(carRepo, times(1)).findByCarId(Mockito.anyLong());
    }

    private void verifyFindAllCarsIsCalledOnce() {
        Mockito.verify(carRepo, times(1)).findAll();
    }


}
