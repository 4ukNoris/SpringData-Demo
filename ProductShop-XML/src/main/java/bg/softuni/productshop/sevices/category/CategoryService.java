package bg.softuni.productshop.sevices.category;

import bg.softuni.productshop.domain.models.category.CategoriesProductsSummaryXMLDto;

import java.io.IOException;

public interface CategoryService {

    CategoriesProductsSummaryXMLDto getAllCategoriesOrderByProductsCount() throws IOException;
}
