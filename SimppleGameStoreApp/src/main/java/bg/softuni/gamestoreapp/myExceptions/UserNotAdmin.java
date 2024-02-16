package bg.softuni.gamestoreapp.myExceptions;

public class UserNotAdmin extends RuntimeException {

    public UserNotAdmin(String reason) {
        super(reason);
    }
}
