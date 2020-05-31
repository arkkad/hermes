package maestro.sevices;

import maestro.dto.NewProductDTO;
import maestro.model.Category;
import maestro.model.Product;
import maestro.repo.CategoriesRepo;
import maestro.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService implements IProductService {

    private final ProductRepo productRepo;
    private final CategoriesRepo categoriesRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, CategoriesRepo categoriesRepo) {
        this.productRepo = productRepo;
        this.categoriesRepo = categoriesRepo;
    }

    @Override
    public boolean addProduct(NewProductDTO newProductDTO, String filename) {
        try {
            Set<Category> cats = new HashSet<>();
            for (String c : newProductDTO.getCategories()) {
                cats.add(categoriesRepo.findByName(c));
            }
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
    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }
}
