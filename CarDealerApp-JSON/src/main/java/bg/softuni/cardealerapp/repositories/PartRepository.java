package bg.softuni.cardealerapp.repositories;

import bg.softuni.cardealerapp.domain.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {

    @Query(value = "SELECT * FROM cars_dealer.parts ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Part> getRandomPartsInRange3And5();

}
