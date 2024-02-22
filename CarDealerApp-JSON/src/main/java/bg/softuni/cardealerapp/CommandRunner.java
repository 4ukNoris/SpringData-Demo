package bg.softuni.cardealerapp;

import bg.softuni.cardealerapp.services.car.CarService;
import bg.softuni.cardealerapp.services.customer.CustomerService;
import bg.softuni.cardealerapp.services.seed.SeedService;
import bg.softuni.cardealerapp.services.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final CustomerService customerService;
    private final CarService carService;
    private final SupplierService supplierService;

    @Autowired
    public CommandRunner(SeedService seedService, CustomerService customerService, CarService carService, SupplierService supplierService) {
        this.seedService = seedService;
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
    }

    @Override
    public void run(String... args) throws Exception {

//        this.seedService.seedAll();

//        this.customerService.findAllCustomerOrderByBirthDateOrderByYoungDriver();

//        this.carService.getAllCarsMakeByToyota("Toyota");

//        this.supplierService.getAllSuppliersIsNotImportParts();

//        this.carService.getAllCarsAndHimsParts();

        this.customerService.getAllCustomersWithTotalSales();

        // todo: От String към LocalDateTime
//        System.out.println("Time");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String localDate ="1970-05-01T00:00:00";
//        localDate = localDate.replace("T", " ");
//        LocalDateTime dateOfBirth = LocalDateTime.parse(localDate, formatter);
//        System.out.println(dateOfBirth);

        // todo: от LocalDateTime към String
//        System.out.println("Time");
//        String localDate = "1970-05-01 00:00";
//        localDate = localDate.replace(" ", "T");
//        String dateOfBirth = localDate.concat(":00");
//
//        System.out.println(dateOfBirth);
//        System.out.println("Time");
    }
}
