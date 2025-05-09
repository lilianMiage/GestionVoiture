package fr.miage.lroux.gestionvoiture.service;

import fr.miage.lroux.gestionvoiture.entity.Car;
import fr.miage.lroux.gestionvoiture.repo.RepoCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCar {

    // Inject the repository
    @Autowired
    private RepoCar repoCar;

    // Method to add a car
    public void createCar(Car car) {
        repoCar.findById(car.getCarId())
        .ifPresentOrElse(existingCar -> {
            throw new IllegalArgumentException("Car with ID " + car.getCarId() + " already exists.");
        }, () -> {
            repoCar.save(car);
        });
    }

    // Method to get all cars
    public List<Car> getAllCars() {
         return (List<Car>) repoCar.findAll();
    }

    // Method to get a car by ID
    public Car getCarById(long id) {
        Optional<Car> carOptional = repoCar.findById(id);
        if (carOptional.isEmpty()) {
            throw new IllegalArgumentException("Car with ID " + id + " does not exist.");
        }
        return carOptional.get();
    }

    // Method to delete a car by ID
    public void deleteCar(long id) {
        Optional<Car> carOptional = repoCar.findById(id);
        if (carOptional.isEmpty()) {
            throw new IllegalArgumentException("Car with ID " + id + " does not exist.");
        }
        repoCar.deleteById(id);
    }
}
