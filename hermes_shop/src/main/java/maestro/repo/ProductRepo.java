package maestro.repo;

import maestro.model.Category;
import maestro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProductRepo extends JpaRepository<Product, Long> {
//    @Query("SELECT ")
//    Set<Product> findByCategory(Category category);
}
