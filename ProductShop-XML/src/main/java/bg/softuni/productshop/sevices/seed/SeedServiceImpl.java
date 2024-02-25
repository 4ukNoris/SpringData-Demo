package bg.softuni.productshop.sevices.seed;

import bg.softuni.productshop.constants.Paths;
import bg.softuni.productshop.constants.Utils;
import bg.softuni.productshop.domain.models.category.CategoryInsertXMLDto;
import bg.softuni.productshop.domain.models.product.ProductInsertXMLDto;
import bg.softuni.productshop.domain.models.user.UserInsertXMLDto;
import bg.softuni.productshop.domain.entities.Category;
import bg.softuni.productshop.domain.entities.Product;
import bg.softuni.productshop.domain.entities.User;
import bg.softuni.productshop.repositories.CategoryRepository;
import bg.softuni.productshop.repositories.ProductRepository;
import bg.softuni.productshop.repositories.UserRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class SeedServiceImpl implements SeedService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void seedUsers() throws IOException, JAXBException {
        if (this.userRepository.count() > 0)
            return;

        List<User> users = getAllUsersFromXml();

        this.userRepository.saveAll(users);
    }

    @Override
    public void seedCategories() throws IOException, JAXBException {
        if (this.categoryRepository.count() == 0)
            return;

        List<Category> categories = getAllCategoriesFromXml();

        this.categoryRepository.saveAll(categories);
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {
        if (this.productRepository.count() > 0)
            return;

        List<Product> products = getAllProductsFromXml();

        this.productRepository.saveAll(products);
    }

    private Product setRandomCategories(Product product) {
        Random random = new Random();
        Long count = this.categoryRepository.count();
        int numberOfCategories = random.nextInt(count.intValue());

        Set<Category> categories = new HashSet<>();
        IntStream.range(0, numberOfCategories)
                .forEach(numb -> {
                    Category category = this.categoryRepository
                            .getRandomCategory().orElseThrow(NoSuchElementException::new);

                    categories.add(category);
                });

        product.setCategories(categories);
        return product;
    }

    private Product setRandomBuyer(Product product) {

        if (product.getPrice().compareTo(BigDecimal.valueOf(700)) > 0) {
            User buyer = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

            while (buyer.equals(product.getSeller())) {
                buyer = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
            }

            product.setBuyer(buyer);
        }
        return product;
    }

    private Product setRandomSeller(Product product) {
        User seller = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

        product.setSeller(seller);
        return product;
    }

    private List<User> getAllUsersFromXml() throws IOException, JAXBException {
        FileReader fileReader = new FileReader(Paths.READ_USER_XML_PATH.toFile());

        JAXBContext context = JAXBContext.newInstance(UserInsertXMLDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        UserInsertXMLDto userDto = (UserInsertXMLDto) unmarshaller.unmarshal(fileReader);

        fileReader.close();

        return userDto.getUsers()
                .stream()
                .map(user -> Utils.MODEL_MAPPER.map(user, User.class))
                .toList();
    }

    private List<Category> getAllCategoriesFromXml() throws IOException, JAXBException {

        FileReader fileReader = new FileReader(Paths.READ_CATEGORY_XML_PATH.toFile());

        JAXBContext context = JAXBContext.newInstance(CategoryInsertXMLDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        CategoryInsertXMLDto categoryDto = (CategoryInsertXMLDto) unmarshaller.unmarshal(fileReader);

        fileReader.close();

        return categoryDto.getCategories()
                .stream()
                .map(category -> Utils.MODEL_MAPPER.map(category, Category.class))
                .toList();
    }

    private List<Product> getAllProductsFromXml() throws IOException, JAXBException {

        FileReader fileReader = new FileReader(Paths.READ_PRODUCT_XML_PATH.toFile());

        JAXBContext context = JAXBContext.newInstance(ProductInsertXMLDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ProductInsertXMLDto productDto = (ProductInsertXMLDto) unmarshaller.unmarshal(fileReader);

        fileReader.close();

        return productDto.getProducts()
                .stream()
                .map(product -> Utils.MODEL_MAPPER.map(product, Product.class))
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .map(this::setRandomCategories)
                .toList();
    }
}
