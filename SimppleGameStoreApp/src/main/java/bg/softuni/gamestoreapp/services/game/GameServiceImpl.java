package bg.softuni.gamestoreapp.services.game;

import bg.softuni.gamestoreapp.constants.ErrorMessage;
import bg.softuni.gamestoreapp.entities.games.*;
import bg.softuni.gamestoreapp.myExceptions.GameExistException;
import bg.softuni.gamestoreapp.repositories.GameRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game addingGame(GameAddDto gameData) {

        Game gameForAdding = gameData.toGame();

        Optional<Game> game = this.gameRepository.findFirstByTitle(gameForAdding.getTitle());
        if (game.isPresent()) {
            throw new GameExistException(String.format(ErrorMessage.GAME_ALREADY_EXIST, game.get().getTitle()));
        }


        return this.gameRepository.save(gameForAdding);
    }

    @Override
    @Transactional
    public Game editGame(Map<String, String> updateArgumentsMap, int gameId) {
        Optional<Game> gameForUpdate = this.gameRepository.findGameById(gameId);
        checkExistingGame(gameForUpdate);

        //GameEditDto gameEditDto = new GameEditDto(gameForUpdate);
        GameEditDto gameEditDto = this.mapper.map(gameForUpdate.get(), GameEditDto.class);
        gameEditDto.updateFields(updateArgumentsMap);

       // Game gameAfterUpdate = this.mapper.map(gameEditDto, Game.class);
        Game gameAfterUpdate = gameEditDto.toGame();

        return this.gameRepository.saveAndFlush(gameAfterUpdate);
    }

    @Override
    @Transactional
    public String deleteGame(int gameId) {
        Optional<Game> deletedGame = this.gameRepository.findGameById(gameId);
        checkExistingGame(deletedGame);
        String gameTitle = deletedGame.get().getTitle();
        this.gameRepository.deleteById(gameId);
        return gameTitle;
    }

    @Override
    public List<String> allGames() {
        List<Game> allGames = this.gameRepository.findAll();
        List<String> titleAndPricesList = new ArrayList<>();

        for (Game game : allGames) {
            GamePriceTitleDto gameDto = mapper.map(game, GamePriceTitleDto.class);

            titleAndPricesList.add(gameDto.toString());
        }
        return titleAndPricesList;
    }

    @Override
    public String gameDetails(String gameTitle) {
        Optional<Game> game = this.gameRepository.findFirstByTitle(gameTitle);
        checkExistingGame(game);

        GameInfoDto gameInfoDto = this.mapper.map(game, GameInfoDto.class);
        return gameInfoDto.toString();
    }

    @Override
    @Transactional
    public Game addGameToUser(String gameTitle) {
        Optional<Game> foundGame = this.gameRepository.findFirstByTitle(gameTitle);
        checkExistingGame(foundGame);
        return foundGame.get();
    }

    private static void checkExistingGame(Optional<Game> game) {
        if (game.isEmpty()) {
            throw new GameExistException(ErrorMessage.GAME_NOT_EXIST);
        }
    }
}
// LoginUser|ivan@ivan.com|Ivan12
// AddGame|Overwatch|100.00|15.5|FqnKB22pOC0|https://us.battle.net/forums/static/images/social-thumbs/overwatch.png|Overwatch is a team-based multiplayer online first-person shooter video game developed and published by Blizzard Entertainment.|24-05-2016
// EditGame|1|price=80.00|size=12.0
// DeleteGame|1
// PurchaseGame|Overwatch
// OwnedGames
