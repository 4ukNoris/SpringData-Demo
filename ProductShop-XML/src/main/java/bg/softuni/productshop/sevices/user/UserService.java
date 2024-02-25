package bg.softuni.productshop.sevices.user;

import bg.softuni.productshop.domain.models.user.UserWithProductsWrapperDto;
import bg.softuni.productshop.domain.models.user.UsersWithSoldProductsXMLDto;

import java.io.IOException;

public interface UserService {
    UsersWithSoldProductsXMLDto findAllBySellingProductsBuyerIsNotNull() throws IOException;

    UserWithProductsWrapperDto getUsersAndHimsProducts() throws IOException;
}
