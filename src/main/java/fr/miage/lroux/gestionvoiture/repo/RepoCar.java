package fr.miage.lroux.gestionvoiture.repo;

import fr.miage.lroux.gestionvoiture.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoCar extends CrudRepository<Car, Long> {

    /**
     * Finds all cars associated with a specific station ID.
     *
     * @param stationId the ID of the station
     * @return a list of cars associated with the given station ID
     */
    List<Car> findByStationId(long stationId);
}
