package bg.softuni.cardealerapp.services.supplier;

import bg.softuni.cardealerapp.domain.dtos.outputsDtos.SupplierLocalDto;

import java.io.IOException;
import java.util.List;

public interface SupplierService {

    List<SupplierLocalDto> getAllSuppliersIsNotImportParts() throws IOException;
}
