package bg.softuni.gamestoreapp.entities.users;

public class LoginUserDto {
    private String email;
    private String password;

    public LoginUserDto(String[] commandTypeParts) {
        this.email = commandTypeParts[1];
        this.password = commandTypeParts[2];

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
