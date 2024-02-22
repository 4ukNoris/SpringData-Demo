package bg.softuni.cardealerapp.services.seed;

import bg.softuni.cardealerapp.constants.Paths;
import bg.softuni.cardealerapp.constants.Utils;
import bg.softuni.cardealerapp.domain.dtos.insertsDtos.CarInsertDto;
import bg.softuni.cardealerapp.domain.dtos.insertsDtos.CustomerInsertDto;
import bg.softuni.cardealerapp.domain.dtos.insertsDtos.PartInsertDto;
import bg.softuni.cardealerapp.domain.dtos.insertsDtos.SupplierInsertDto;
import bg.softuni.cardealerapp.domain.entities.*;
import bg.softuni.cardealerapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class SeedServiceImpl implements SeedService {

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public SeedServiceImpl(SupplierRepository supplierRepository,
                           PartRepository partRepository, CarRepository carRepository,
                           CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void seedSuppliers() throws IOException {
        if (this.supplierRepository.count() == 0) {
            FileReader fileReader = new FileReader(Paths.READ_SUPPLIERS_JSON_PATH.toFile());
            List<Supplier> suppliers =
                    Arrays.stream(Utils.GSON.fromJson(fileReader, SupplierInsertDto[].class))
                            .map(supplierInsertDto -> Utils.MAPPER.map(supplierInsertDto, Supplier.class))
                            .toList();

            this.supplierRepository.saveAllAndFlush(suppliers);
            fileReader.close();
        }
    }

    @Override
    public void seedParts() throws IOException {
        if (this.partRepository.count() == 0) {
            FileReader fileReader = new FileReader(Paths.READ_PARTS_JSON_PATH.toFile());
            List<Part> parts =
                    Arrays.stream(Utils.GSON.fromJson(fileReader, PartInsertDto[].class))
                            .map(partInsertDto -> Utils.MAPPER.map(partInsertDto, Part.class))
                            .map(this::setRandomPart)
                            .toList();

            this.partRepository.saveAllAndFlush(parts);
            fileReader.close();
        }
    }

    @Override
    public void seedCars() throws IOException {
        if (this.carRepository.count() == 0) {
            FileReader fileReader = new FileReader(Paths.READ_CARS_JSON_PATH.toFile());
            List<Car> cars =
                    Arrays.stream(Utils.GSON.fromJson(fileReader, CarInsertDto[].class))
                            .map(carInsertDto -> Utils.MAPPER.map(carInsertDto, Car.class))
                            .map(this::setPartsBetween3And5)
                            .toList();

            this.carRepository.saveAllAndFlush(cars);
            fileReader.close();
        }
    }

    @Override
    public void seedCustomers() throws IOException {
        if (this.customerRepository.count() == 0) {
            FileReader fileReader = new FileReader(Paths.READ_CUSTOMERS_JSON_PATH.toFile());
            List<Customer> customers =
                    Arrays.stream(Utils.GSON.fromJson(fileReader, CustomerInsertDto[].class))
                            .map(customerInsertDto -> Utils.MAPPER.map(customerInsertDto, Customer.class))
                            .toList();
            this.customerRepository.saveAllAndFlush(customers);
            fileReader.close();
        }
    }


    @Override
    public void seedSales() {
        if (this.saleRepository.count() == 0) {
            List<Sale> sales = this.carRepository.getAllCars()
                    .stream()
                    .map(sale -> Utils.MAPPER.map(sale, Sale.class))
                    .map(this::setRandomCar)
                    .map(this::setRandomCustomer)
                    .map(this::setRandomDiscount)
                    .toList();

            this.saleRepository.saveAllAndFlush(sales);
        }


    }

    private Sale setRandomDiscount(Sale sale) {
        Random random = new Random();
        double[] discounts = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5};

        int randomIndex = random.nextInt(0, discounts.length);
        double discount = discounts[randomIndex];

        if (sale.getCustomer().isYoungDriver()) {
            sale.setDiscount(discount + 0.05);
        } else {
            sale.setDiscount(discount);
        }
        return sale;
    }

    private Sale setRandomCar(Sale sale) {
        Car car = this.carRepository.getRandomCar().orElseThrow(NoSuchElementException::new);
        sale.setCar(car);

        return sale;
    }

    private Sale setRandomCustomer(Sale sale) {
        Customer customer = this.customerRepository.getRandomCustomer().orElseThrow(NoSuchElementException::new);
        sale.setCustomer(customer);

        return sale;
    }

    private Part setRandomPart(Part part) {
        Supplier supplier = this.supplierRepository.getRandomSupplier().orElseThrow(NoSuchElementException::new);
        part.setSupplier(supplier);

        return part;
    }

    private Car setPartsBetween3And5(Car car) {
        Random random = new Random();
        int numberOfParts = random.nextInt(3, 6);

        Set<Part> parts = new HashSet<>();
        IntStream.range(0, numberOfParts)
                .forEach(number -> {
                    Part part = this.partRepository.getRandomPartsInRange3And5().orElseThrow(NoSuchElementException::new);
                    parts.add(part);
                });

        car.setParts(parts);
        return car;
    }
}
