package bg.softuni.productshop.sevices.product;

import bg.softuni.productshop.domain.models.product.ExportProductsInRangeDto;
import bg.softuni.productshop.domain.models.product.ProductWithAttributesDto;
import bg.softuni.productshop.domain.entities.Product;
import bg.softuni.productshop.domain.entities.User;
import bg.softuni.productshop.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    private TypeMap<Product, ProductWithAttributesDto> productToDtoMapping;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapper = new ModelMapper();

        this.productToDtoMapping = getProductToDtoMapping();
    }


    @Override
    @Transactional
    public ExportProductsInRangeDto findAllProductsByPriceAndWithoutBuyer(BigDecimal low, BigDecimal high) {
        List<Product> products =
                this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceDesc(low, high);

        List<ProductWithAttributesDto> dtos = products
                .stream()
                .map(this.productToDtoMapping::map)
                .toList();

        return new ExportProductsInRangeDto(dtos);
    }

    private TypeMap<Product, ProductWithAttributesDto> getProductToDtoMapping() {

    // Използваме този конвертор да преобразува User към String винаги когато имаме User
        Converter<User, String> userToFullNameConverter =
                context -> context.getSource() == null ? null : context.getSource().getFullName();

        TypeMap<Product, ProductWithAttributesDto> typeMap =
                this.mapper.createTypeMap(Product.class, ProductWithAttributesDto.class);

    // Извикваме нашия къстам конвертор всеки пък когато искаме да прехвърлим информация от гетСелър към сетСелър
        this.productToDtoMapping = typeMap.addMappings(map-> map.using(userToFullNameConverter)
                .map(Product::getSeller, ProductWithAttributesDto::setSeller));

        return productToDtoMapping;
    }
}
