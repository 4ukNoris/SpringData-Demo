package bg.softuni.cardealerapp.domain.dtos.outputsDtos;

import bg.softuni.cardealerapp.domain.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CustomerDto {

    private int Id;

    private String Name;

    private String BirthDate;

    private boolean IsYoungDriver;

    private List<Sale> Sales;

    public CustomerDto() {
        this.Sales = new ArrayList<>();
    }
    public void setBirthDate(LocalDateTime birthDate) {
        String localDate = birthDate.toString();
        String dateOfBirth = localDate.concat(":00");
        this.BirthDate = dateOfBirth;
    }
}
