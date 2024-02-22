package bg.softuni.cardealerapp.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Column
    private String name;

    @Column(name = "is_importer")
    private boolean isImporter;

    @OneToMany(targetEntity = Part.class, mappedBy = "supplier")
    @Fetch(FetchMode.JOIN)
    private Set<Part> parts;
}
