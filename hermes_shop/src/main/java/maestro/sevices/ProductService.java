package maestro.sevices;

import maestro.dto.NewProductDTO;
import maestro.model.Product;
import maestro.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService implements IProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public boolean addProduct(NewProductDTO newProductDTO, String filename) {
        try {
            Set<String> cats = new HashSet<>(newProductDTO.getCategories());
            Product product = new Product.Builder()
                    .withName(newProductDTO.getName())
                    .withDesc(newProductDTO.getDescription())
                    .withPrice(newProductDTO.getPrice())
                    .withStorageCount(newProductDTO.getStorageCount())
                    .withFilename(filename)
                    .withCategories(cats)
                    .build();
            productRepo.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteProductByName(String name) {
        productRepo.delete(productRepo.findByName(name));
    }

    @Override
    public List<Product> findAllProducts() {
        List<Product> all = productRepo.findAll();
        return all;
    }
}
