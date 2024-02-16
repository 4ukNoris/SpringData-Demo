package bg.softuni.gamestoreapp.services.user;

import bg.softuni.gamestoreapp.entities.games.Game;
import bg.softuni.gamestoreapp.entities.users.LoginUserDto;
import bg.softuni.gamestoreapp.entities.users.RegisterDto;
import bg.softuni.gamestoreapp.entities.users.User;

import java.util.Optional;

public interface UserService {
    User register(RegisterDto registerData);

    Optional<User> login(LoginUserDto loginData);

    User getLoggedUser();

    void logout();

    void buyGame(Game game);
}
