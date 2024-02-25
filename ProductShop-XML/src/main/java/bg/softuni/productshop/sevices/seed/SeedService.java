package bg.softuni.productshop.sevices.seed;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface SeedService {
    void seedUsers() throws IOException, JAXBException;

    void seedCategories() throws IOException, JAXBException;

    void seedProducts() throws IOException, JAXBException;

    default void seedAll() throws IOException, JAXBException {
        seedUsers();
        seedCategories();
        seedProducts();
    }
}
