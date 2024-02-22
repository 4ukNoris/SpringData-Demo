package bg.softuni.cardealerapp.domain.dtos.outputsDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarToyotaDto {

    private int Id;

    private String Make;

    private String Model;

    private BigInteger TravelledDistance;
}
