package bg.softuni.gamestoreapp.entities.games;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GameInfoDto {
    private int id;
    private String title;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public GameInfoDto() {
    }

    public GameInfoDto(String title, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.setReleaseDate(releaseDate);
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

    @Override
    public String toString() {
        // Променям формата на датата по мой шаблон и го обръщам в стринг
        String date = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(this.getReleaseDate());
        return String.format("Title: %s\n" +
                "Price: %.2f \n" +
                "Description: %s. \n" +
                "Release date: %s\n", this.getTitle(), this.getPrice(), this.getDescription(), date);
    }
}
