package maestro.sevices;

import maestro.dto.NewProductDTO;
import maestro.exceptions.UnknownEntityException;
import maestro.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface IProductService {

    List<Product> findAll();

    List<Product> findByCategory(String category);

    Product getProduct(Long productId) throws UnknownEntityException;

    boolean createProduct(NewProductDTO newProductDTO, String filename);

    List<Product> findByAvailability(String available);

    void deleteProductByName(String name);

    Product getProductByName(String name);

    Page<Product> findByCategories(String category, PageRequest pageable);
}

