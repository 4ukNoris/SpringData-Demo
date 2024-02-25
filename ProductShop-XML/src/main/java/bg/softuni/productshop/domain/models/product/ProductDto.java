package bg.softuni.productshop.domain.models.product;

import bg.softuni.productshop.domain.models.category.CategoryDto;
import bg.softuni.productshop.domain.models.user.UserDto;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDto {

    private String name;

    private BigDecimal price;

    private UserDto buyer;

    private UserDto seller;

    private Set<CategoryDto> categories;

    public ProductWithAttributesDto toProductInRangeDto() {
        return new ProductWithAttributesDto(name, price, seller.getFullName());
    }

    public static ProductSoldWithCountDto toProductSoldWithCountDto(Set<ProductDto> sellingProducts) {
        return new ProductSoldWithCountDto(sellingProducts
                .stream()
                .map(ProductDto::toProductBasicInfoDto)
                .toList());
    }

    public static ProductBasicInfoDto toProductBasicInfoDto(ProductDto productDto) {
        return new ProductBasicInfoDto(productDto.getName(), productDto.getPrice());
    }
}
