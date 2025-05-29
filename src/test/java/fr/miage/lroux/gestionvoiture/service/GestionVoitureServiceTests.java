package fr.miage.lroux.gestionvoiture.controller.service;

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

}
