package bg.softuni.gamestoreapp.entities.users;

import bg.softuni.gamestoreapp.constants.ErrorMessage;
import bg.softuni.gamestoreapp.constants.Validation;
import bg.softuni.gamestoreapp.myExceptions.ValidationException;

import java.util.regex.Pattern;

public class RegisterDto {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    /**
     * Validate the data for registering a user
     *
     * Email validation...
     * Password validation...
     *
     *  commandParts[0] is skipped because it contains the command name
     *  which is not needed in teh user object
     *
     * @param commandParts all data read from the console
     */
    public RegisterDto(String[] commandParts) {
        this.email = commandParts[1];
        this.password = commandParts[2];
        this.confirmPassword = commandParts[3];
        this.fullName = commandParts[4];

        this.validate();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }
    private void validate() {

        boolean isValidEmail = Pattern.matches(Validation.EMAIL_PATTERN, this.email);
        if (!isValidEmail) {
            throw new ValidationException(ErrorMessage.INVALID_EMAIL);
        }

        boolean isValidPassword = Pattern.matches(Validation.PASSWORD_PATTERN, this.password);
        if (!isValidPassword) {
            throw new ValidationException(ErrorMessage.PASS_MISS_MATCH);
        }

        if (!this.password.equals(this.confirmPassword)) {
            throw new ValidationException(ErrorMessage.PASS_MISS_MATCH);
        }
    }
}
