package bg.softuni.cardealerapp.domain.dtos.insertsDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarInsertDto {

    private String make;

    private String model;

    private BigInteger travelledDistance;
}
