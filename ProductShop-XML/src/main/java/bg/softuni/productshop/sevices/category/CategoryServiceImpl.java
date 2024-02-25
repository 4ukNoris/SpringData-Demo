package bg.softuni.productshop.sevices.category;

import bg.softuni.productshop.constants.Utils;
import bg.softuni.productshop.domain.models.category.CategoriesProductsSummaryXMLDto;
import bg.softuni.productshop.domain.models.category.CategoryProductsSummaryDto;
import bg.softuni.productshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoriesProductsSummaryXMLDto getAllCategoriesOrderByProductsCount() {
        List<CategoryProductsSummaryDto> categories = this.categoryRepository.findAllCategoryGroupByNameAndOrderByCount()
                .stream()
                .map(category -> Utils.MODEL_MAPPER.map(category, CategoryProductsSummaryDto.class))
                .toList();
        return new CategoriesProductsSummaryXMLDto(categories);
    }
}
