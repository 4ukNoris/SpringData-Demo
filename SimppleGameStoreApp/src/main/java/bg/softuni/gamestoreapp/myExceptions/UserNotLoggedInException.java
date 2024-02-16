package bg.softuni.gamestoreapp.myExceptions;

public class UserNotLoggedInException extends RuntimeException {

    public UserNotLoggedInException(String reason) {
        super(reason);
    }
}
