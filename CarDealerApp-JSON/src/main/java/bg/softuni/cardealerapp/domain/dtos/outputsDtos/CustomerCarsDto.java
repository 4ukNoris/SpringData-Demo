package bg.softuni.cardealerapp.domain.dtos.outputsDtos;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCarsDto {

    @SerializedName("fullName")
    private String name;

    private Long boughtCars;

    private BigDecimal spentMoney;
}
