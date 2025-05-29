package fr.miage.lroux.gestionvoiture.repo;

import fr.miage.lroux.gestionvoiture.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCar extends CrudRepository<Car, Long> {

}
