package fr.miage.lroux.gestionvoiture.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
public class Car {

    @Id
    @GeneratedValue
    private long carId;

    private String brand;

    private String model;

    private double batteryLevel; // niveau de batterie

    private double kilometresTravelled; // km parcourus

    private int numberOfSeats; // nombre de places

    private boolean used; // savoir s'il est dispo ou non

    @ElementCollection
    private List<Double> localisation; // localisation GPS

    // TODO Association avec l'utilisateur
    //private User user;

    // Constructeur par défaut
    public Car() {
    }

    /**
     * Constructor for Car.
     * @param brand
     * @param model
     * @param batteryLevel
     * @param kilometresTravelled
     * @param numberOfSeats
     * @param used
     */
    public Car(String brand, String model, double batteryLevel, double kilometresTravelled, int numberOfSeats, boolean used) {
        this.brand = brand;
        this.model = model;
        this.batteryLevel = batteryLevel;
        this.kilometresTravelled = kilometresTravelled;
        this.numberOfSeats = numberOfSeats;
        this.used = used;
    }

    /**
     * Constructor for Car with carId.
     * @param carId
     * @param brand
     * @param model
     * @param batteryLevel
     * @param kilometresTravelled
     * @param numberOfSeats
     * @param used
     */
    public Car(long carId, String brand, String model, double batteryLevel, double kilometresTravelled, int numberOfSeats, boolean used) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.batteryLevel = batteryLevel;
        this.kilometresTravelled = kilometresTravelled;
        this.numberOfSeats = numberOfSeats;
        this.used = used;
    }
    public long getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public double getKilometresTravelled() {
        return kilometresTravelled;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public boolean isUsed() {
        return used;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void setKilometresTravelled(double kilometresTravelled) {
        this.kilometresTravelled = kilometresTravelled;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

}
