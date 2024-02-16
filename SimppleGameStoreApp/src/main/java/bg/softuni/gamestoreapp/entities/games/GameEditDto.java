package bg.softuni.gamestoreapp.entities.games;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class GameEditDto {
    private String title;
    private BigDecimal price;
    private float size;
    private String trailerId;
    private String thumbnailUrl;
    private String description;
    private LocalDate releaseDate;

    public GameEditDto() {}

    public GameEditDto(Optional<Game> foundedGame) {
        this.setTitle(foundedGame.get().getTitle());
        this.setPrice(foundedGame.get().getPrice());
        this.setSize(foundedGame.get().getSize());
        this.setTrailerId(foundedGame.get().getTrailerId());
        this.setThumbnailUrl(foundedGame.get().getThumbnailUrl());
        this.setDescription(foundedGame.get().getDescription());
        this.setReleaseDate(foundedGame.get().getReleaseDate());
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
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
    public void updateFields(Map<String, String> updatedValues) {
        for (String key : updatedValues.keySet()) {

            this.setTitle(title);
            if (Objects.equals(key, "title")) {
                setTitle(updatedValues.get(key));
            }
            this.setPrice(price);
            if (Objects.equals(key, "price")) {
                setPrice(new BigDecimal(updatedValues.get(key)));
            }
            this.setSize(size);
            if (Objects.equals(key, "size")) {
                setSize(Float.parseFloat(updatedValues.get(key)));
            }
            this.setTrailerId(trailerId);
            if (Objects.equals(key, "trailerId")) {
                setTrailerId(updatedValues.get(key));
            }
            this.setThumbnailUrl(thumbnailUrl);
            if (Objects.equals(key, "thumbnailUrl")) {
                setThumbnailUrl(updatedValues.get(key));
            }
            this.setDescription(description);
            if (Objects.equals(key, "description")) {
                setDescription(updatedValues.get(key));
            }
            this.setReleaseDate(releaseDate);
            if (Objects.equals(key, "releaseDate")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                setReleaseDate(LocalDate.parse(updatedValues.get(key), formatter));
            }
        }
    }

    public Game toGame() {
        return new Game(title,
                price,
                size,
                trailerId,
                thumbnailUrl,
                description,
                releaseDate);
    }
}
