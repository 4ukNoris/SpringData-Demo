package bg.softuni.productshop.repositories;

import bg.softuni.productshop.domain.models.category.CategoryProductsSummaryDto;
import bg.softuni.productshop.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM categories ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Category> getRandomCategory();

    @Query("SELECT new bg.softuni.productshop.domain.models.category.CategoryProductsSummaryDto" +
            "(c.name, count(p.id), AVG(p.price), SUM(p.price))" +
            " FROM Product p" +
            " JOIN p.categories c" +
            " GROUP BY c.id" +
            " ORDER BY count(p.id)desc")
    List<CategoryProductsSummaryDto> findAllCategoryGroupByNameAndOrderByCount();
}
