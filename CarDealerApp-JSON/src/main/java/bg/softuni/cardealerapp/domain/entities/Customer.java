package bg.softuni.cardealerapp.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column
    private String name;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "is_young_driver")
    private boolean isYoungDriver;

    public Customer(String name, String birthDate, boolean isYoungDriver) {
        this.name = name;
        this.setBirthDate(birthDate);
        this.isYoungDriver = isYoungDriver;
    }

    public void setBirthDate(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String localDate = birthDate;
        localDate = localDate.replace("T", " ");
        LocalDateTime dateOfBirth = LocalDateTime.parse(localDate, formatter);
        this.birthDate = dateOfBirth;
    }
}
