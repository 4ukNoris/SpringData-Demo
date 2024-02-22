package bg.softuni.cardealerapp.services.customer;

import bg.softuni.cardealerapp.constants.Paths;
import bg.softuni.cardealerapp.constants.Utils;
import bg.softuni.cardealerapp.domain.dtos.outputsDtos.CustomerCarsDto;
import bg.softuni.cardealerapp.domain.dtos.outputsDtos.CustomerDto;
import bg.softuni.cardealerapp.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    @Transactional
    public void findAllCustomerOrderByBirthDateOrderByYoungDriver() throws IOException {
        List<CustomerDto> customersDtos =
                this.customerRepository.findAllCustomerOrderByBirthDateOrderByYoungDriver()
                        .stream()
                        .map(customer -> Utils.MAPPER.map(customer, CustomerDto.class))
                        .toList();
        Utils.writeJSONIntoFile(customersDtos, Paths.ORDERED_CUSTOMERS_JSON_PATH);
    }

    @Override
    public List<CustomerCarsDto> getAllCustomersWithTotalSales() throws IOException {
        List<CustomerCarsDto> customersWithCars = this.customerRepository.findAllCustomerCountsCarsAndPrice();

        Utils.writeJSONIntoFile(customersWithCars, Paths.CUSTOMERS_TOTAL_SALES_JSON_PATH);
        return customersWithCars;
    }
}
