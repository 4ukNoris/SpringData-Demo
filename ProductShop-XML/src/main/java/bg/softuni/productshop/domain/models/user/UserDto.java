package bg.softuni.productshop.domain.models.user;

import bg.softuni.productshop.domain.models.product.ProductDto;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import static bg.softuni.productshop.domain.models.product.ProductDto.toProductSoldWithCountDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDto {

    private String firstName;

    private String lastName;

    private Integer age;

    private Set<ProductDto> sellingProducts;

    private Set<ProductDto> boughtProducts;

    private Set<UserDto> friends;

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }


    public UserWithProductsDto toUserWithProductsDto() {
        return new UserWithProductsDto(firstName, lastName, age, toProductSoldWithCountDto(sellingProducts));
    }


}
