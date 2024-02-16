package bg.softuni.gamestoreapp.entities.games;

import bg.softuni.gamestoreapp.constants.ErrorMessage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GameAddDto {
    private int id;
    private String title;
    private BigDecimal price;
    private float size;
    private String trailerId;
    private String thumbnailUrl;
    private String description;
    private LocalDate releaseDate;

    public GameAddDto(String[] commandParts) {
        this.setTitle(commandParts[1]);
        this.setPrice(new BigDecimal(commandParts[2]));
        this.setSize(Float.parseFloat(commandParts[3]));
        this.setTrailerId(commandParts[4]);
        this.setThumbnailUrl(commandParts[5]);
        this.setDescription(commandParts[6]);
        this.setReleaseDate(commandParts[7]);
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

    protected void setTitle(String title) {
        char firstLetter = title.charAt(0);
        if (title.length() < 3 || Character.isLowerCase(firstLetter)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_TITLE);
        }
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_PRICE);
        }
        this.price = price;
    }

    public float getSize() {
        return size;
    }

    private void setSize(float size) {
        if (size <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_SIZE);
        }
        this.size = size;
    }

    public String getTrailerId() {
        return trailerId;
    }

    private void setTrailerId(String trailerId) {
        this.trailerId = trailerId.substring(trailerId.length() - 11);
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    private void setThumbnailUrl(String thumbnailUrl) {
        if (thumbnailUrl.startsWith("http://") || thumbnailUrl.startsWith("https://")) {
            this.thumbnailUrl = thumbnailUrl;
        } else {
            throw new IllegalArgumentException(ErrorMessage.INVALID_URL_PROTOCOL);
        }
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description.substring(description.length() - 20);
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    private void setReleaseDate(String inputDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.releaseDate = LocalDate.parse(inputDate, formatter);

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
