package bg.softuni.productshop.sevices.product;

import bg.softuni.productshop.domain.models.product.ExportProductsInRangeDto;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.math.BigDecimal;

public interface ProductService {
    ExportProductsInRangeDto findAllProductsByPriceAndWithoutBuyer(BigDecimal low, BigDecimal high) throws IOException, JAXBException;
}
