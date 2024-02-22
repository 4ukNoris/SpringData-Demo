package bg.softuni.cardealerapp.domain.dtos.insertsDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierInsertDto {

    private String name;

    private boolean isImporter;

}
