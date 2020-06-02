package maestro.repo;

import maestro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
//    @Query("SELECT ")
//    Set<Product> findByCategory(Category category);

    Product findByName(String name);
}
