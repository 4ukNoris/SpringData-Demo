package bg.softuni.gamestoreapp.services.user;

import bg.softuni.gamestoreapp.constants.ErrorMessage;
import bg.softuni.gamestoreapp.entities.games.Game;
import bg.softuni.gamestoreapp.entities.users.LoginUserDto;
import bg.softuni.gamestoreapp.entities.users.RegisterDto;
import bg.softuni.gamestoreapp.entities.users.User;
import bg.softuni.gamestoreapp.myExceptions.UserNotLoggedInException;
import bg.softuni.gamestoreapp.myExceptions.ValidationException;
import bg.softuni.gamestoreapp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private User currentUser;
    private final UserRepository userRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.currentUser = null;
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterDto registerData) {

        User userForRegistration = mapper.map(registerData, User.class);

        long userCount = this.userRepository.count();

        if (userCount == 0) {
            userForRegistration.setAdmin(true);
        }

        boolean isEmailFound = this.userRepository.findByEmail(userForRegistration.getEmail()).isPresent();
        if (isEmailFound) {
            throw new ValidationException(ErrorMessage.EMAIL_ALREADY_EXISTS);
        }
        return this.userRepository.save(userForRegistration);
    }

    @Override
    public Optional<User> login(LoginUserDto loginData) {
        Optional<User> user = this.userRepository.
                findByEmailAndPassword(loginData.getEmail(), loginData.getPassword());

        if (user.isEmpty()) {
            throw new ValidationException(ErrorMessage.USERNAME_OR_PASSWORD_NOT_VALID);
        }
        if (currentUser != null) {
            throw new ValidationException(ErrorMessage.USER_ALREADY_LOGGED);
        }
        user.ifPresent(value -> this.currentUser = value);

        return user;
    }

    public User getLoggedUser() {
        if (this.currentUser == null) {
            throw new UserNotLoggedInException(ErrorMessage.NOT_LOGGED_IN_USER);
        }
        return this.currentUser;
    }
    @Override
    public void logout() {
        if (this.currentUser == null) {
            throw new UserNotLoggedInException(ErrorMessage.NOT_LOGGED_OUT_USER);
        }
        this.currentUser = null;
    }

    @Override
    @Transactional
    public void buyGame(Game game) {
        User user = getLoggedUser();
        user.getGames().add(game);
        this.userRepository.saveAndFlush(user);
    }
}
