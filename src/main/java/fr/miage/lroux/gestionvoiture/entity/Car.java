package fr.miage.lroux.gestionvoiture.entity;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a Car.
 */
@Entity
public class Car {

    /**
     * Car ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long carId;

    /**
     * Car brand.
     */
    private String brand;

    /**
     * Car model.
     */
    private String model;

    /**
     * Battery level of the car.
     */
    private double batteryLevel;

    /**
     * Kilometres travelled by the car.
     */
    private double kilometresTravelled;

    /**
     * Number of seats in the car.
     */
    private int numberOfSeats;

    /**
     * Indicates if the car is currently in use.
     */
    private boolean used;

    /**
     * GPS localisation of the car.
     * This is a list of doubles representing latitude and longitude.
     */
    @ElementCollection
    private List<Double> localisation;

    /**
     * ID of the station where the car is parked.
     */
    private long stationId;

    /**
     * Default constructor for JPA.
     */
    public Car() {
    }

    /**
     * Constructor for Car.
     *
     * @param brand              Car's brand
     * @param model              Car's model
     * @param batteryLevel       Battery level of the car
     * @param kilometresTravelled Kilometres travelled by the car
     * @param numberOfSeats      Number of seats in the car
     * @param used               Indicates if the car is currently in use
     * @param localisation       GPS localisation of the car
     */
    public Car (long carId, String brand, String model, double batteryLevel, double kilometresTravelled, int numberOfSeats, boolean used,List<Double> localisation, long stationId) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.batteryLevel = batteryLevel;
        this.kilometresTravelled = kilometresTravelled;
        this.numberOfSeats = numberOfSeats;
        this.used = used;
        this.localisation = localisation; // Initialisation de la localisation GPS
        this.stationId = stationId;
    }

    /**
     * Constructor for Car without carId.
     *
     * @param brand              Car's brand
     * @param model              Car's model
     * @param batteryLevel       Battery level of the car
     * @param kilometresTravelled Kilometres travelled by the car
     * @param numberOfSeats      Number of seats in the car
     * @param used               Indicates if the car is currently in use
     * @param localisation       GPS localisation of the car
     */
    public Car (String brand, String model, double batteryLevel, double kilometresTravelled, int numberOfSeats, boolean used,List<Double> localisation) {
        this.brand = brand;
        this.model = model;
        this.batteryLevel = batteryLevel;
        this.kilometresTravelled = kilometresTravelled;
        this.numberOfSeats = numberOfSeats;
        this.used = used;
        this.localisation = localisation;
    }

    /**
     * Get the car ID.
     *
     * @return Car's ID
     */
    public long getCarId() {
        return carId;
    }

    /**
     * Get the car's brand.
     *
     * @return Car's brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Get the car's model.
     *
     * @return Car's model
     */
    public String getModel() {
        return model;
    }

    /**
     * Get the battery level of the car.
     *
     * @return Battery level of the car
     */
    public double getBatteryLevel() {
        return batteryLevel;
    }

    /**
     * Get the kilometres travelled by the car.
     *
     * @return Kilometres travelled by the car
     */
    public double getKilometresTravelled() {
        return kilometresTravelled;
    }

    /**
     * Get the number of seats in the car.
     *
     * @return Number of seats in the car
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Check if the car is currently in use.
     *
     * @return true if the car is in use, false otherwise
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Get the GPS localisation of the car.
     *
     * @return List of doubles representing latitude and longitude
     */
    public List<Double> getLocalisation() {
        return localisation;
    }

    /**
     * Get the ID of the station where the car is parked.
     *
     * @return Station ID
     */
    public void setCarId(long carId) {
        this.carId = carId;
    }

    /**
     * Set the car's brand.
     *
     * @param brand Car's brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Set the car's model.
     *
     * @param model Car's model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Set the battery level of the car.
     *
     * @param batteryLevel Battery level of the car
     */
    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    /**
     * Set the kilometres travelled by the car.
     *
     * @param kilometresTravelled Kilometres travelled by the car
     */
    public void setKilometresTravelled(double kilometresTravelled) {
        this.kilometresTravelled = kilometresTravelled;
    }

    /**
     * Set the number of seats in the car.
     *
     * @param numberOfSeats Number of seats in the car
     */
    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Set if the car is currently in use.
     *
     * @param used true if the car is in use, false otherwise
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * Set the GPS localisation of the car.
     *
     * @param localisation List of doubles representing latitude and longitude
     */
    public void setLocalisation(List<Double> localisation) {
        this.localisation = localisation;
    }

    /**
     * Get the ID of the station where the car is parked.
     *
     * @return Station ID
     */
    public long getStationId() {
        return stationId;
    }

    /**
     * Set the ID of the station where the car is parked.
     *
     * @param stationId Station ID
     */
    public void setStationId(long stationId) {
        this.stationId = stationId;
    }
}
