package bg.softuni.gamestoreapp.entities.orders;

import bg.softuni.gamestoreapp.entities.games.Game;
import bg.softuni.gamestoreapp.entities.users.User;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User buyer;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Game> products;

    public Order() {
//        this.products = new HashSet<>();
    }

    public Order(User buyer, Set<Game> products) {
        this.buyer = buyer;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Set<Game> getProducts() {
        return products;
    }

    public void setProducts(Set<Game> products) {
        this.products = products;
    }
    public void addProduct(Game game) {
        this.products.add(game);
    }
    public void removeProduct(Game game) {
        this.products.remove(game);
    }
}
