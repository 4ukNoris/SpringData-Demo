package bg.softuni.productshop.domain.models.product;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSoldWithCountDto {

    @XmlAttribute
    private Integer count;


    @XmlElement(name = "product")
    private List<ProductBasicInfoDto> products;

    public ProductSoldWithCountDto(List<ProductBasicInfoDto> products) {
        this.products = products;
        this.count = products.size();
    }
}
