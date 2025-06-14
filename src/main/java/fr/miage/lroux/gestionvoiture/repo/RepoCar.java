package fr.miage.lroux.gestionvoiture.repo;

import fr.miage.lroux.gestionvoiture.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepoCar extends CrudRepository<Car, Long> {

    List<Car> findAllByStationId(long stationId);
}
