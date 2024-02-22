package bg.softuni.cardealerapp.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private Integer quantity;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Supplier supplier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(name, part.name)
                && Objects.equals(price, part.price)
                && Objects.equals(quantity, part.quantity)
                && Objects.equals(getId(), part.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, getId());
    }
}
