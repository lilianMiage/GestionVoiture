package fr.miage.lroux.gestionvoiture.service;

import fr.miage.lroux.gestionvoiture.entity.Car;
import fr.miage.lroux.gestionvoiture.repo.RepoCar;
import fr.miage.lroux.gestionvoiture.service.ServiceCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GestionVoitureServiceTests {

    @Mock
    private RepoCar repoCar;

    @InjectMocks
    private ServiceCar carService;

    private Car car;

    @BeforeEach
    public void setUp() {
        car = new Car("Porsche", "911 GT3 RS", 100.0, 1200.0, 2, false, List.of(54.0,48.0));
        car.setCarId(1L);
    }

    @Test
    public void testCreateCar_WhenCarDoesNotExist_ShouldSaveAndReturnCar() {
        // Arrange
        when(repoCar.findById(1L)).thenReturn(Optional.empty());
        when(repoCar.save(car)).thenReturn(car);

        // Act
        Car result = carService.createCar(car);

        // Assert
        assertNotNull(result);
        assertEquals(car.getCarId(), result.getCarId());
        verify(repoCar).findById(1L);
        verify(repoCar).save(car);
    }

    @Test
    public void testCreateCar_WhenCarExists_ShouldThrowException() {
        // Arrange
        when(repoCar.findById(1L)).thenReturn(Optional.of(car));

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            carService.createCar(car);
        });

        assertEquals("Car with ID 1 already exists.", exception.getMessage());
        verify(repoCar).findById(1L);
        verify(repoCar, never()).save(any());
    }

    @Test
    public void testGetCarById_WhenCarExists_ShouldReturnCar() {
        // Arrange
        when(repoCar.findById(1L)).thenReturn(Optional.of(car));

        // Act
        Car result = carService.getCarById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(car.getCarId(), result.getCarId());
        verify(repoCar).findById(1L);
    }

    @Test
    public void testGetCarById_WhenCarDoesNotExist_ShouldThrowException() {
        // Arrange
        when(repoCar.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            carService.getCarById(1L);
        });

        assertEquals("Car with ID 1 does not exist.", exception.getMessage());
        verify(repoCar).findById(1L);
    }

    @Test
    public void testDeleteCarById_WhenCarExists_ShouldDeleteCar() {
        // Arrange
        when(repoCar.findById(1L)).thenReturn(Optional.of(car));

        // Act
        carService.deleteCarById(1L);

        // Assert
        verify(repoCar).findById(1L);
        verify(repoCar).deleteById(1L);
    }

    @Test
    public void testDeleteCarById_WhenCarDoesNotExist_ShouldThrowException() {
        // Arrange
        when(repoCar.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            carService.deleteCarById(1L);
        });

        assertEquals("Car with ID 1 does not exist.", exception.getMessage());
        verify(repoCar).findById(1L);
        verify(repoCar, never()).deleteById(anyLong());
    }

    @Test
    public void testPutUsedToTrue_ShouldSetUsedTrueAndStationIdZero() {
        // Arrange
        when(repoCar.findById(1L)).thenReturn(Optional.of(car));
        when(repoCar.save(any(Car.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Car result = carService.putUsedToTrue(1L);

        // Assert
        assertTrue(result.isUsed());
        assertEquals(0L, result.getStationId());
        verify(repoCar).findById(1L);
        verify(repoCar).save(result);
    }

    @Test
    public void testUpdateAfterLocation_ShouldUpdateCarFields() {
        // Arrange
        Car carUpdate = new Car("Porsche", "911 GT3 RS", 75.0, 1300.0, 5, true, List.of(54.0, 48.0));
        carUpdate.setStationId(3L);
        when(repoCar.findById(1L)).thenReturn(Optional.of(car));
        when(repoCar.save(any(Car.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Car result = carService.updateAfterLocation(1L, carUpdate);

        // Assert
        assertFalse(result.isUsed());
        assertEquals(75.0, result.getBatteryLevel());
        assertEquals(1300.0, result.getKilometresTravelled());
        assertEquals(3L, result.getStationId());
        verify(repoCar).findById(1L);
        verify(repoCar).save(result);
    }

    @Test
    public void testUpdateStationId_ShouldUpdateStationId() {
        // Arrange
        when(repoCar.findById(1L)).thenReturn(Optional.of(car));
        when(repoCar.save(any(Car.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Car result = carService.updateStationId(1L, 7L);

        // Assert
        assertEquals(7L, result.getStationId());
        verify(repoCar).findById(1L);
        verify(repoCar).save(result);
    }

    @Test
    public void testGetCarsByStation_ShouldReturnCarsAtStation() {
        // Arrange
        List<Car> carsAtStation = List.of(car);
        when(repoCar.findAllByStationId(2L)).thenReturn(carsAtStation);

        // Act
        List<Car> result = carService.getCarsByStation(2L);

        // Assert
        assertEquals(1, result.size());
        assertEquals(car.getCarId(), result.get(0).getCarId());
        verify(repoCar).findAllByStationId(2L);
    }
}
