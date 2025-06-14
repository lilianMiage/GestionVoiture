package fr.miage.lroux.gestionvoiture.controller;

import fr.miage.lroux.gestionvoiture.entity.Car;
import fr.miage.lroux.gestionvoiture.service.ServiceCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing car operations.
 * Provides endpoints to create, retrieve, update, and delete cars.
 */
@RestController
@RequestMapping("/api/car")
public class ControllerCar {

    /**
     * Service for car operations.
     * This service handles the business logic for car management.
     */
    @Autowired
    private ServiceCar serviceCar;

    /**
     * Creates a new car.
     * @param car The car to be created.
     * @return The created car.
     */
    @PostMapping("create")
    public Car createCar(@RequestBody Car car){
        return serviceCar.createCar(car);
    }

    /**
     * Retrieves a car by its ID.
     * @param id The ID of the car to retrieve.
     * @return The car with the specified ID.
     * @throws IllegalArgumentException If the car does not exist.
     */
    @GetMapping("/{id}")
    public Car getUser(@PathVariable Long id) throws IllegalArgumentException {
        return serviceCar.getCarById(id);
    }


    @GetMapping("/cars/{stationId}")
    public List<Car> getVoitures(@PathVariable Long stationId) {
        return serviceCar.getAllCarsByStationId(stationId);
    }

    /**
     * Deletes a car by its ID.
     * @param id The ID of the car to delete.
     * @throws IllegalArgumentException If the car does not exist.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id) throws IllegalArgumentException {
        serviceCar.deleteCarById(id);
    }

    /**
     * Marks a car as used and clears its station ID.
     * @param id The ID of the car to update.
     * @return The updated car.
     */
    @PutMapping("/{id}/use")
    public Car setUsedToTrueAndClearStation(@PathVariable Long id) {
        return serviceCar.putUsedToTrue(id);
    }

    /**
     * Updates a car's details after it has been returned.
     * @param id The ID of the car to update.
     * @param car The car object containing updated details.
     * @return The updated car.
     */
    @PutMapping("/{id}/return")
    public Car updateReturnCar(@PathVariable Long id,@RequestBody Car car){
        return serviceCar.updateAfterLocation(id,car);
    }

    /**
     * Updates the station ID of a car.
     * @param carId The ID of the car to update.
     * @param stationId The new station ID to set for the car.
     * @return The updated car.
     */
    @PutMapping("{carId}")
    public Car updateStationId(@PathVariable long carId, @RequestBody long stationId){
        return serviceCar.updateStationId(carId, stationId);
    }

    /**
     * Retrieves all cars associated with a specific station ID.
     * @param stationId The ID of the station to filter cars by.
     * @return A list of cars associated with the specified station ID.
     */
    @GetMapping("cars/{stationId}")
    public List<Car> getCarsByStationId(@PathVariable long stationId){
        return serviceCar.getCarsByStation(stationId);
    }
}
