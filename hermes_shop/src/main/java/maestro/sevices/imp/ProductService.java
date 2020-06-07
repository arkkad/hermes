package maestro.sevices.imp;

import maestro.dto.NewProductDTO;
import maestro.exceptions.UnknownEntityException;
import maestro.model.Product;
import maestro.repo.ProductRepo;
import maestro.sevices.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    @Transactional
    public boolean createProduct(NewProductDTO newProductDTO, String filename) {
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
    public List<Product> findByAvailability(String available) {
        List<Product> products;
        if ("all".equals(available))
            products = productRepo.findAll();
        else {
            products = productRepo.findAll().stream()
                    .filter((product -> product.getStorageCount() > 0))
                    .collect(Collectors.toList());
        }
        return products;
    }

    @Override
    @Transactional
    public void deleteProductByName(String name) {
        productRepo.delete(productRepo.findByName(name));
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductByName(String name) {
        return productRepo.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepo.findAll().stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCategory(String category) {
        return null;
    }

    @Override
    @Transactional
    public Product getProduct(Long productId) throws UnknownEntityException {
        return productRepo.findById(productId)
                .orElseThrow(()-> new UnknownEntityException(Product.class, productId));
    }
}
