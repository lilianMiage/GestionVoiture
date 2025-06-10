package fr.miage.lroux.gestionvoiture.controller;

import fr.miage.lroux.gestionvoiture.entity.Car;
import fr.miage.lroux.gestionvoiture.service.ServiceCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class ControllerCar {

    @Autowired
    private ServiceCar serviceCar;

    @PostMapping("create")
    public Car createCar(@RequestBody Car car){
        return serviceCar.createCar(car);
    }

    @GetMapping("/{id}")
    public Car getUser(@PathVariable Long id) throws IllegalArgumentException {
        return serviceCar.getCarById(id);
    }

    @GetMapping("/cars/{stationId}")
    public List<Car> getVoitures(@PathVariable Long stationId) {
        return serviceCar.getAllCarsByStationId(stationId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id) throws IllegalArgumentException {
        serviceCar.deleteCarById(id);
    }

}
