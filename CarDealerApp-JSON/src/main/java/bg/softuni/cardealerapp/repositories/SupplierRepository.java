package bg.softuni.cardealerapp.repositories;

import bg.softuni.cardealerapp.domain.dtos.outputsDtos.SupplierLocalDto;
import bg.softuni.cardealerapp.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    @Query(value = "SELECT * FROM cars_dealer.suppliers ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Supplier> getRandomSupplier();

    @Query("SELECT new bg.softuni.cardealerapp.domain.dtos.outputsDtos.SupplierLocalDto" +
            "(s.id, s.name, count(p.id))" +
            " FROM Supplier s" +
            " JOIN s.parts p" +
            " WHERE s.isImporter = false" +
            " GROUP BY s.id")
    List<SupplierLocalDto> findAllByImporterFalseAndParts();
}
