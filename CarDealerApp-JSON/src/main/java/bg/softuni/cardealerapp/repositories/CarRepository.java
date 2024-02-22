package bg.softuni.cardealerapp.repositories;

import bg.softuni.cardealerapp.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query(value = "SELECT * FROM cars_dealer.cars ORDER BY rand() LIMIT 1", nativeQuery = true)
    Optional<Car> getRandomCar();

    @Query(value = "SELECT * FROM cars_dealer.cars", nativeQuery = true)
    List<Car> getAllCars();

    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

    @Query("SELECT c FROM Car c" +
            " JOIN c.parts p ")
    List<Car> findAllCarsAndParts();
}
