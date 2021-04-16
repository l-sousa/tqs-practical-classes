package com.tqs.lab4.car;


import com.tqs.lab4.car.JsonUtil;
import com.tqs.lab4.car.model.Car;
import com.tqs.lab4.car.services.CarManagerService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CarControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CarManagerService cms;

    @Test
    void whenPostCar_thenReturnCreatedCar() throws Exception {
        Car mazda2 = new Car("Mazda", "2");
        mazda2.setCarId(111L);
        when(cms.save(Mockito.any())).thenReturn(mazda2);

        mvc.perform(
                post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(mazda2))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Mazda")));

        verify(cms, times(1)).save(mazda2);
    }

    @Test
    void whenGetCarByValidId_thenReturnOneCar() throws Exception {
        Car car = new Car("Citroen", "2cv");
        car.setCarId(111L);

        when(cms.getCarDetails(car.getCarId())).thenReturn(Optional.of(car));

        mvc.perform(MockMvcRequestBuilders.get("/api/cars/111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("maker").value("Citroen"))
                .andExpect(jsonPath("model").value("2cv"));
    }


    @Test
    void whenGetAllCars_thenReturnAllCars() throws Exception {
        Car c1 = new Car("Mercedes", "Class A");
        Car c2 = new Car("Mercerdes", "Class B");
        Car c3 = new Car("Mercedes", "Class C");

        List<Car> cars = Arrays.asList(c1, c2, c3);

        given(cms.getAllCars()).willReturn(cars);

        mvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(c1.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(c2.getMaker())));
    }

}