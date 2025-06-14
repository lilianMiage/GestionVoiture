package fr.miage.lroux.gestionvoiture.service;

import fr.miage.lroux.gestionvoiture.entity.Car;
import fr.miage.lroux.gestionvoiture.repo.RepoCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing car operations.
 * Provides methods to create, retrieve, update, and delete cars.
 */
@Service
public class ServiceCar {

    /**
     * Repository for car operations.
     * This repository provides CRUD operations for Car entities.
     */
    @Autowired
    private RepoCar repoCar;

    /**
     * Creates a new car.
     * @param car The car to be created.
     * @return The created car.
     * @throws IllegalArgumentException If a car with the same ID already exists.
     */
    public Car createCar(Car car) {
        Optional<Car> carOptional = repoCar.findById(car.getCarId());
        if (carOptional.isPresent()) {
            throw new IllegalArgumentException("Car with ID " + car.getCarId() + " already exists.");
        }
        repoCar.save(car);
        return car;
    }

    public List<Car> getAllCarsByStationId(long stationId) {
         return (List<Car>) repoCar.findAllByStationId(stationId);
    }

    /**
     * Retrieves a car by its ID.
     * @param id The ID of the car to retrieve.
     * @return The car with the specified ID.
     * @throws IllegalArgumentException If the car does not exist.
     */
    public Car getCarById(long id) {
        Optional<Car> carOptional = repoCar.findById(id);
        if (carOptional.isEmpty()) {
            throw new IllegalArgumentException("Car with ID " + id + " does not exist.");
        }
        return carOptional.get();
    }

    /**
     * Retrieves a car by its ID.
     * @param id The ID of the car to retrieve.
     * @return The car with the specified ID.
     * @throws IllegalArgumentException If the car does not exist.
     */
    public void deleteCarById(long id) {
        Optional<Car> carOptional = repoCar.findById(id);
        if (carOptional.isEmpty()) {
            throw new IllegalArgumentException("Car with ID " + id + " does not exist.");
        }
        repoCar.deleteById(id);
    }

    /**
     * Marks a car as used and clears its station ID.
     * @param id The ID of the car to update.
     * @return The updated car.
     */
    public Car putUsedToTrue(long id){
        Car car = getCarById(id);
        car.setUsed(true);
        car.setStationId(0L);
        repoCar.save(car);
        return car;
    }

    /**
     * Updates a car's details after it has been returned.
     * @param carId The ID of the car to update.
     * @param carUpdate The car object containing updated details.
     * @return The updated car.
     */
    public Car updateAfterLocation(long carId,Car carUpdate){
        Car carExisting = getCarById(carId);
        carExisting.setUsed(false);
        carExisting.setKilometresTravelled(carUpdate.getKilometresTravelled());
        carExisting.setBatteryLevel(carUpdate.getBatteryLevel());
        carExisting.setStationId(carUpdate.getStationId());
        repoCar.save(carExisting);
        return carExisting;
    }

    /**
     * Updates the station ID of a car.
     * @param carId The ID of the car to update.
     * @param stationId The new station ID to set for the car.
     * @return The updated car.
     */
    public Car updateStationId(long carId,long stationId){
        Car car = getCarById(carId);
        car.setStationId(stationId);
        repoCar.save(car);
        return car;
    }

    /**
     * Retrieves all cars associated with a specific station.
     * @param stationId The ID of the station to filter cars by.
     * @return A list of cars associated with the specified station.
     */
    public List<Car> getCarsByStation(long stationId){
        return repoCar.findByStationId(stationId);
    }
}
