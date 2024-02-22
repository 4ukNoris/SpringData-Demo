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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @Column
    private Double discount;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Customer customer;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Car car;
}
