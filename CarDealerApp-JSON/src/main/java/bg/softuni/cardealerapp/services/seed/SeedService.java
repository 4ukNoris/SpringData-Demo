package bg.softuni.cardealerapp.services.seed;

import java.io.IOException;

public interface SeedService {

    void seedSuppliers() throws IOException;
    void seedParts() throws IOException;

    void seedCars() throws IOException;
    void seedCustomers() throws IOException;
    void seedSales();

    default void seedAll() throws IOException{
        this.seedSuppliers();
        this.seedParts();
        this.seedCars();
        this.seedCustomers();
        this.seedSales();
    }
}
