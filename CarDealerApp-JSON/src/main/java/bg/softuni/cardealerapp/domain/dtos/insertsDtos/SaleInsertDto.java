package bg.softuni.cardealerapp.domain.dtos.insertsDtos;

import bg.softuni.cardealerapp.domain.entities.Car;
import bg.softuni.cardealerapp.domain.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleInsertDto {

    private Double discount;

    private Customer customer;

    private Car car;

}
