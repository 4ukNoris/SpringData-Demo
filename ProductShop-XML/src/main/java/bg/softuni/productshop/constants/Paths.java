package bg.softuni.productshop.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path READ_USER_XML_PATH =
            Path.of("src", "main", "resources", "XML_dbContent", "users.xml");
    public static final Path READ_CATEGORY_XML_PATH =
            Path.of("src", "main", "resources", "XML_dbContent", "categories.xml");
    public static final Path READ_PRODUCT_XML_PATH =
            Path.of("src", "main", "resources", "XML_dbContent", "products.xml");
    public static final Path PRODUCTS_IN_RANGE_XML_PATH =
            Path.of("src", "main", "resources", "XML_outputs", "products-in-range.xml");
    public static final Path USERS_SOLD_PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "XML_outputs", "users-sold-products.xml");
    public static final Path CATEGORIES_BY_PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "XML_outputs", "categories-by-products.xml");
    public static final Path USERS_AND_PRODUCTS_XML_PATH =
            Path.of("src", "main", "resources", "XML_outputs", "users-and-products.xml");
}
