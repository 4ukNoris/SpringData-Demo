package bg.softuni.cardealerapp.domain.dtos.insertsDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartInsertDto {

    private String name;

    private BigDecimal price;

    private Integer quantity;
}
