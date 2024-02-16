package bg.softuni.gamestoreapp.repositories;

import bg.softuni.gamestoreapp.entities.games.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    Optional<Game> findGameById(int gameId);

    Optional<Game> findFirstByTitle(String title);

}
