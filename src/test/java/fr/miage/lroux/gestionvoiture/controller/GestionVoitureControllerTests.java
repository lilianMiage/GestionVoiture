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

    /**
     * MockMvc instance for testing the controller.
     * This instance is used to perform requests and assert responses.
     */
    @Autowired
    private MockMvc mvc;

    /**
     * Repository for Car operations.
     * This repository is used to interact with the database for Car entities.
     */
    @Autowired
    private RepoCar repoCar;

    /**
     * Car instance used for testing.
     * This instance is created before each test to ensure a consistent state.
     */
    private Car car;

    /**
     * Sets up the test environment by creating a new Car instance.
     * This method is called before each test to ensure a fresh state.
     */
    @BeforeEach
    public void setUp(){
        car = new Car("Porsche", "911 GT3 RS", 100.0, 1200.0, 2, false, List.of(54.0,48.0));
        car = repoCar.save(car);
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

    @Test
    public void testSetUsedToTrueAndClearStation() throws Exception {
        mvc.perform(put("/api/car/" + car.getCarId() + "/use"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.used",  is(true)))
                .andExpect(jsonPath("$.stationId", is(0)));
    }

    @Test
    public void testUpdateReturnCar() throws Exception {
        car.setBatteryLevel(80.0);
        car.setKilometresTravelled(1500.0);

        ObjectMapper om = new ObjectMapper();
        String carJson = om.writeValueAsString(car);

        mvc.perform(put("/api/car/" + car.getCarId() + "/return")
                        .contentType("application/json")
                        .content(carJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.batteryLevel",is(80.0)))
                .andExpect(jsonPath("$.kilometresTravelled",is(1500.0)));
    }

    @Test
    public void testUpdateStationId() throws Exception {
        mvc.perform(put("/api/car/" + car.getCarId())
                        .contentType("application/json")
                        .content("5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stationId").value(5));
    }
}
