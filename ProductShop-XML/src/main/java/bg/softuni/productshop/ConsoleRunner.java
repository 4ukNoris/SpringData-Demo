package bg.softuni.productshop;

import bg.softuni.productshop.constants.Paths;
import bg.softuni.productshop.constants.Utils;
import bg.softuni.productshop.domain.models.category.CategoriesProductsSummaryXMLDto;
import bg.softuni.productshop.domain.models.product.ExportProductsInRangeDto;
import bg.softuni.productshop.domain.models.user.UserWithProductsWrapperDto;
import bg.softuni.productshop.domain.models.user.UsersWithSoldProductsXMLDto;
import bg.softuni.productshop.sevices.category.CategoryService;
import bg.softuni.productshop.sevices.product.ProductService;
import bg.softuni.productshop.sevices.seed.SeedService;
import bg.softuni.productshop.sevices.user.UserService;
import jakarta.xml.bind.JAXBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ConsoleRunner(SeedService seedService, ProductService productService, UserService userService, CategoryService categoryService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

//        this.seedService.seedAll();

//        this.productInRange();
//        this.findUserWithSoldProducts();
//        this.findAllCategoriesOrderByProductCount();

        this.getUsersCountAndProducts();
    }

    private void getUsersCountAndProducts() throws IOException, JAXBException {

        UserWithProductsWrapperDto usersAndHimsProducts = this.userService.getUsersAndHimsProducts();

        Utils.writeXmlIntoFile(usersAndHimsProducts, Paths.USERS_AND_PRODUCTS_XML_PATH);
    }

    private void findAllCategoriesOrderByProductCount() throws JAXBException, IOException {

        CategoriesProductsSummaryXMLDto categoriesCounts = this.categoryService.getAllCategoriesOrderByProductsCount();

        Utils.writeXmlIntoFile(categoriesCounts, Paths.CATEGORIES_BY_PRODUCTS_XML_PATH);
    }

    private void findUserWithSoldProducts() throws IOException, JAXBException {
        UsersWithSoldProductsXMLDto usersProducts = this.userService.findAllBySellingProductsBuyerIsNotNull();

        Utils.writeXmlIntoFile(usersProducts, Paths.USERS_SOLD_PRODUCTS_XML_PATH);
    }

    private void productInRange() throws JAXBException, IOException {

        ExportProductsInRangeDto inRange = this.productService.findAllProductsByPriceAndWithoutBuyer(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        Utils.writeXmlIntoFile(inRange, Paths.PRODUCTS_IN_RANGE_XML_PATH);

    }
}
