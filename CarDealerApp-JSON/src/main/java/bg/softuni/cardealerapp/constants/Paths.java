package bg.softuni.cardealerapp.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path READ_SUPPLIERS_JSON_PATH =
            Path.of("src", "main", "resources", "dbInputData", "suppliers.json");
    public static final Path READ_PARTS_JSON_PATH =
            Path.of("src", "main", "resources", "dbInputData", "parts.json");
    public static final Path READ_CARS_JSON_PATH =
            Path.of("src", "main", "resources", "dbInputData", "cars.json");
    public static final Path READ_CUSTOMERS_JSON_PATH =
            Path.of("src", "main", "resources", "dbInputData", "customers.json");


    public static final Path ORDERED_CUSTOMERS_JSON_PATH =
            Path.of("src", "main", "resources", "outputData", "ordered-customers.json");
    public static final Path TOYOTA_CARS_JSON_PATH =
            Path.of("src", "main", "resources", "outputData", "toyota-cars.json");
    public static final Path LOCAL_SUPPLIERS_JSON_PATH =
            Path.of("src", "main", "resources", "outputData", "local-suppliers.json");
    public static final Path CARS_AND_PARTS_JSON_PATH =
            Path.of("src", "main", "resources", "outputData", "cars-and-parts.json");
    public static final Path CUSTOMERS_TOTAL_SALES_JSON_PATH =
            Path.of("src", "main", "resources", "outputData", "customers-total-sales.json");
}
