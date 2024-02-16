package bg.softuni.gamestoreapp.myExceptions;

public class GameExistException extends RuntimeException {
    public GameExistException(String reason) {
        super(reason);
    }
}
