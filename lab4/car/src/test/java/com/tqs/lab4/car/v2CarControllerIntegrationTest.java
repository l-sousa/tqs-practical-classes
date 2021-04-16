package com.tqs.lab4.car;

import com.tqs.lab4.car.model.Car;
import com.tqs.lab4.car.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-integrationtest.properties")
public class v2CarControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepo;

    @AfterEach
    public void resetDB() {
        carRepo.deleteAll();
    }

    @Test
    public void whenValidInput_thenCreateCar() {
        Car c1 = new Car("Mercedes", "Class A");
        ResponseEntity<Car> res = restTemplate.postForEntity("/api/cars", c1, Car.class);

        List<Car> queryResult = carRepo.findAll();
        assertThat(queryResult).extracting(Car::getMaker).containsOnly("Mercedes");
    }


    @Test
    public void givenEmployees_whenGetEmployees_thenStatus200() {
        createTestCar("Mercedes", "Class A");
        createTestCar("BMW", "Series 1");
        createTestCar("Volvo", "s40");


        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("Mercedes", "BMW", "Volvo");

    }

    private void createTestCar(String maker, String model) {
        Car c = new Car(maker, model);
        carRepo.saveAndFlush(c);
    }

}