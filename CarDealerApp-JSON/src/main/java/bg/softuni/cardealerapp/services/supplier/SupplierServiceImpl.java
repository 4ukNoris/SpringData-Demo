package bg.softuni.cardealerapp.services.supplier;

import bg.softuni.cardealerapp.constants.Paths;
import bg.softuni.cardealerapp.constants.Utils;
import bg.softuni.cardealerapp.domain.dtos.outputsDtos.SupplierLocalDto;
import bg.softuni.cardealerapp.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierLocalDto> getAllSuppliersIsNotImportParts() throws IOException {
        List<SupplierLocalDto> suppliers = this.supplierRepository.findAllByImporterFalseAndParts();

        Utils.writeJSONIntoFile(suppliers, Paths.LOCAL_SUPPLIERS_JSON_PATH);
        return suppliers;
    }
}
