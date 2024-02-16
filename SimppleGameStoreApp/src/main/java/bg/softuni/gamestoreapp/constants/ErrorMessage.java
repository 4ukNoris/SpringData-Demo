package bg.softuni.gamestoreapp.constants;

public enum ErrorMessage {
    ;
    public final static String PASS_MISS_MATCH = "Password  must be at least 6 symbols," +
            " contain at least 1 uppercase, 1 lowercase letter and 1 digit";
    public final static String USERNAME_OR_PASSWORD_NOT_VALID = "Incorrect username / password";
    public final static String INVALID_EMAIL = "Incorrect email.";
    public final static String EMAIL_ALREADY_EXISTS = "Email already exists.";
    public final static String USER_ALREADY_LOGGED = "Logged in user can logout.";
    public final static String NOT_LOGGED_OUT_USER = "Cannot log out. No user was logged in.";
    public final static String NOT_LOGGED_IN_USER = "No user was logged in.";
    public final static String USER_IS_NOT_ADMIN = "This is Admin operation!";
    public final static String GAME_ALREADY_EXIST = "The game %s already in the base!";
    public final static String GAME_NOT_EXIST = "The game %s not in the base!";
    public final static String INVALID_URL_PROTOCOL = "Missing \"http:\" or \"https:\" protocol!";
    public final static String NEGATIVE_PRICE = "The price can't be negative number";
    public final static String NEGATIVE_SIZE = "The size can't be less then zero";
    public final static String INVALID_TITLE = "Title length must be between 3-100 and start with an capital letter";

}
