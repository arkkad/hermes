package maestro.sevices;

import maestro.dto.NewProductDTO;
import maestro.model.Product;

import java.util.List;

public interface IProductService {
    boolean addProduct(NewProductDTO newProductDTO, String filename);
    void deleteProductByName (String name);
    List<Product> findAllProducts();
}
