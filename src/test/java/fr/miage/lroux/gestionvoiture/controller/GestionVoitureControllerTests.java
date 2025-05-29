package fr.miage.lroux.gestionvoiture.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.miage.lroux.gestionvoiture.entity.Car;
import fr.miage.lroux.gestionvoiture.repo.RepoCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class GestionVoitureControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RepoCar repoCar;
    private Car car;

    @BeforeEach
    public void setUp(){
        car = new Car("Porsche", "911 GT3 RS", 100.0, 1200.0, 2, false, List.of(54.0,48.0));
        car = repoCar.save(car);
    }

    @Test
    public void getCarByid() throws Exception {
         mvc.perform(get("/api/car/" + car.getCarId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is(car.getBrand())))
                .andExpect(jsonPath("$.model", is(car.getModel())))
                .andExpect(jsonPath("$.batteryLevel", is(car.getBatteryLevel())))
                .andExpect(jsonPath("$.kilometresTravelled", is(car.getKilometresTravelled())))
                .andExpect(jsonPath("$.numberOfSeats", is(car.getNumberOfSeats())))
                .andExpect(jsonPath("$.used", is(car.isUsed())))
                .andExpect(jsonPath("$.localisation", is(car.getLocalisation())));
    }

    @Test
    public void createCar() throws Exception{
        Car carObject = new Car("Nissan", "GTR", 100.0, 1200.0, 4, false, List.of(54.0,48.0));
        ObjectMapper om = new ObjectMapper();
        String carJson = om.writeValueAsString(carObject);
        mvc.perform(post("/api/car/create")
                        .contentType("application/json")
                        .content(carJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is(carObject.getBrand())))
                .andExpect(jsonPath("$.model", is(carObject.getModel())))
                .andExpect(jsonPath("$.batteryLevel", is(carObject.getBatteryLevel())))
                .andExpect(jsonPath("$.kilometresTravelled", is(carObject.getKilometresTravelled())))
                .andExpect(jsonPath("$.numberOfSeats", is(carObject.getNumberOfSeats())))
                .andExpect(jsonPath("$.used", is(carObject.isUsed())))
                .andExpect(jsonPath("$.localisation", is(carObject.getLocalisation())));
    }

    @Test
    public void deleteCarByid() throws Exception {
        mvc.perform(delete("/api/car/delete/1"))
                .andExpect(status().isOk());
    }
}
