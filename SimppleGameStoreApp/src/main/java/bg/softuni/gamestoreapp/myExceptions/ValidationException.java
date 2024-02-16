package bg.softuni.gamestoreapp.myExceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String reason) {
        super(reason);
    }
}
