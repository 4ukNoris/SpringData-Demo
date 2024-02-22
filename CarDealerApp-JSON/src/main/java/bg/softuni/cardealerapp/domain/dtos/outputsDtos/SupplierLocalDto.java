package bg.softuni.cardealerapp.domain.dtos.outputsDtos;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierLocalDto {

    @SerializedName("Id")
    private int id;

    @SerializedName("Name")
    private String name;

    private Long partsCount;
}
