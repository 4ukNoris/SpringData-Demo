package bg.softuni.gamestoreapp.entities.games;

import java.math.BigDecimal;

public class GamePriceTitleDto {
    private int id;
    private String title;
    private BigDecimal price;

    public GamePriceTitleDto() {}

    public GamePriceTitleDto(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
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

    @Override
    public String toString() {
        return String.format("%s %.2f", this.getTitle(), this.getPrice());
    }
}
