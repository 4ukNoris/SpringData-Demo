package bg.softuni.cardealerapp.domain.dtos.outputsDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarBaseDto {

    private String Make;

    private String Model;

    private BigInteger TravelledDistance;

    private Set<PartNameAndPriceDto> parts;
}
