package bg.softuni.cardealerapp.repositories;

import bg.softuni.cardealerapp.domain.dtos.outputsDtos.CustomerCarsDto;
import bg.softuni.cardealerapp.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT * FROM cars_dealer.customers ORDER BY rand() LIMIT 1", nativeQuery = true)
    Optional<Customer> getRandomCustomer();

    @Query(value = "SELECT * FROM cars_dealer.customers ORDER BY birth_date", nativeQuery = true)
    List<Customer> findAllCustomerOrderByBirthDateOrderByYoungDriver();

    @Query("SELECT new bg.softuni.cardealerapp.domain.dtos.outputsDtos.CustomerCarsDto" +
            "(c.name, count(ca.id), SUM(p.price))" +
            " FROM Sale s" +
            " JOIN s.car ca" +
            " JOIN s.customer c" +
            " JOIN ca.parts p" +
            " GROUP BY c.id")
    List<CustomerCarsDto> findAllCustomerCountsCarsAndPrice();
}
