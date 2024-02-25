package bg.softuni.productshop.sevices.user;

import bg.softuni.productshop.constants.Utils;
import bg.softuni.productshop.domain.models.user.*;
import bg.softuni.productshop.domain.entities.User;
import bg.softuni.productshop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UsersWithSoldProductsXMLDto findAllBySellingProductsBuyerIsNotNull() {
        List<User> users = this.userRepository.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerLastName();

        List<UserWithSoldProductsDto> dtos = users
                .stream()
                .map(user -> Utils.MODEL_MAPPER.map(user, UserWithSoldProductsDto.class))
                .toList();

        return new UsersWithSoldProductsXMLDto(dtos);
    }

    @Override
    @Transactional
    public UserWithProductsWrapperDto getUsersAndHimsProducts() throws IOException {
        List<User> users = this.userRepository.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerLastName();

        List<UserWithProductsDto> usersAndProducts = users
                .stream()
                .map(user -> Utils.MODEL_MAPPER.map(user, UserDto.class))
                .map(UserDto::toUserWithProductsDto)
                .toList();

        return new UserWithProductsWrapperDto(usersAndProducts);
    }
}
