package bg.softuni.cardealerapp.services.customer;

import bg.softuni.cardealerapp.domain.dtos.outputsDtos.CustomerCarsDto;

import java.io.IOException;
import java.util.List;

public interface CustomerService {

    void findAllCustomerOrderByBirthDateOrderByYoungDriver() throws IOException;

    List<CustomerCarsDto> getAllCustomersWithTotalSales() throws IOException;
}
