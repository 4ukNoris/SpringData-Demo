package bg.softuni.gamestoreapp.entities.users;

import bg.softuni.gamestoreapp.constants.Validation;
import bg.softuni.gamestoreapp.entities.games.Game;
import bg.softuni.gamestoreapp.entities.orders.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    @Email(regexp = Validation.EMAIL_PATTERN)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "full_name", nullable = false, unique = true)
    private String fullName;
    @Column(name = "is_admin")
    private boolean isAdmin;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Game> games;

    @OneToMany(targetEntity = Order.class, mappedBy = "buyer")
    private Set<Order> orders;

    public User() {
        this.games = new HashSet<>();
        this.orders = new HashSet<>();
    }

    public User(String email, String password, String fullName) {
        this();
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
