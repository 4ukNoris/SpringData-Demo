package bg.softuni.gamestoreapp.entities.games;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private BigDecimal price;
    private float size;
    @Column(name = "trailer_id", nullable = false)
    private String trailerId;
    @Column(name = "thumbnail_url", nullable = false)
    private String thumbnailUrl;
    private String description;
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    public Game() {}

    public Game(String title, BigDecimal price, float size, String trailerId, String thumbnailUrl, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailerId = trailerId;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }


}
