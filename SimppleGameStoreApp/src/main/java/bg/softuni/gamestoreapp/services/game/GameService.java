package bg.softuni.gamestoreapp.services.game;

import bg.softuni.gamestoreapp.entities.games.Game;
import bg.softuni.gamestoreapp.entities.games.GameAddDto;

import java.util.List;
import java.util.Map;

public interface GameService {
    Game addingGame(GameAddDto gameData);

    Game editGame(Map<String, String> argumentsForUpdate, int gameId);

    String deleteGame(int gameId);

    List<String> allGames();

    String gameDetails(String gameTitle);

    Game addGameToUser(String gameTitle);
}
