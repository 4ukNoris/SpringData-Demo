package bg.softuni.gamestoreapp.services.execute;

import bg.softuni.gamestoreapp.constants.Command;
import bg.softuni.gamestoreapp.constants.ErrorMessage;
import bg.softuni.gamestoreapp.entities.games.Game;
import bg.softuni.gamestoreapp.entities.games.GameAddDto;
import bg.softuni.gamestoreapp.entities.users.LoginUserDto;
import bg.softuni.gamestoreapp.entities.users.RegisterDto;
import bg.softuni.gamestoreapp.entities.users.User;
import bg.softuni.gamestoreapp.myExceptions.UserNotAdmin;
import bg.softuni.gamestoreapp.services.game.GameService;
import bg.softuni.gamestoreapp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExecuteServiceImpl implements ExecutorService {

    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public ExecuteServiceImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public String execute(String commandLine) {
        String[] commandTypeParts = commandLine.split("\\|");

        String commandName = commandTypeParts[0];
        String output = switch (commandName) {
            case Command.REGISTER_USER -> registerUser(commandTypeParts);
            case Command.LOG_IN_USER -> logInUser(commandTypeParts);
            case Command.LOGOUT_USER -> logoutUser();
            case Command.ADD_GAME -> addGame(commandTypeParts);
            case Command.EDIT_GAME -> editGame(commandTypeParts);
            case Command.DELETE_GAME -> deleteGame(commandTypeParts[1]);
            case Command.ALL_GAMES -> getAllGames();
            case Command.DETAILS_GAME -> getDetailsOfGame(commandTypeParts[1]);
            case Command.PURCHASE_GAME -> buyGameByTitleGame(commandTypeParts[1]);
            case Command.OWNED_GAMES -> userCollectionOfGames();
            // TODO: 15.2.2024 г. Need to create methods for orders!
            default -> Command.WRONG_COMMAND;
        };
        return output;
    }

    private String userCollectionOfGames() {
        User loggedUser = this.userService.getLoggedUser();
        Set<Game> userGames = loggedUser.getGames();
        List<String> titleGames = new ArrayList<>();
        userGames.stream().map(Game::getTitle).forEach(titleGames::add);

        return String.join(System.lineSeparator(), titleGames);
    }

    private String buyGameByTitleGame(String commandTypePart) {
        User loggedUser = this.userService.getLoggedUser();
        Game purchasedGame = this.gameService.addGameToUser(commandTypePart);
        loggedUser.getGames().add(purchasedGame);
        this.userService.buyGame(purchasedGame);

        return String.format("Successfully purchase game: %s", purchasedGame.getTitle());
    }

    private String getDetailsOfGame(String commandTypePart) {
        String gameData = this.gameService.gameDetails(commandTypePart);
        return gameData;
    }

    private String getAllGames() {
        List<String> titlesAndPriceGames = this.gameService.allGames();

        return String.join(System.lineSeparator(), titlesAndPriceGames);
    }

    private String deleteGame(String idOfGame) {
        checkLoggedAdminUser();
        int gameId = Integer.parseInt(idOfGame);
        String title = this.gameService.deleteGame(gameId);

        return String.format("Successfully deleted %s", title);
    }

    private String editGame(String[] commandParts) {
        checkLoggedAdminUser();

        Map<String, String> updatingArguments = new HashMap<>();

        for (int i = 2; i < commandParts.length; i++) {
            String[] argumentsForUpdate = commandParts[i].split("=");
            updatingArguments.put(argumentsForUpdate[0], argumentsForUpdate[1]);
        }
        int gameId = Integer.parseInt(commandParts[1]);
        Game game = this.gameService.editGame(updatingArguments, gameId);

        return String.format("Successfully edited %s", game.getTitle());
    }

    private String addGame(String[] commandParts) {
        checkLoggedAdminUser();
        GameAddDto gameData = new GameAddDto(commandParts);
        Game game = this.gameService.addingGame(gameData);

        return String.format("Successfully added %s", game.getTitle());
    }


    private String logoutUser() {
        User loggedUser = this.userService.getLoggedUser();

        this.userService.logout();

        return String.format("User %s successfully logged out", loggedUser.getFullName());
    }

    private String logInUser(String[] commandTypeParts) {
        LoginUserDto loginData = new LoginUserDto(commandTypeParts);
        Optional<User> user = userService.login(loginData);

        return String.format("Successfully logged in %s", user.get().getFullName());
    }

    private String registerUser(String[] commandTypeParts) {
        RegisterDto registerData = new RegisterDto(commandTypeParts);
        User user = userService.register(registerData);

        return String.format("%s was registered", user.getFullName());
    }
    private void checkLoggedAdminUser() {
        User loggedUser = this.userService.getLoggedUser();
        if (!loggedUser.isAdmin()) {
            throw new UserNotAdmin(ErrorMessage.USER_IS_NOT_ADMIN);
        }
    }
}
