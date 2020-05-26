package maestro.controllers;

import maestro.model.Category;
import maestro.repo.CategoriesRepo;
import maestro.repo.ProductRepo;
import maestro.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepo productRepo;
    private final CategoriesRepo categoriesRepo;

    @Autowired
    public ProductController(ProductRepo productRepo, CategoriesRepo categoriesRepo) {
        this.productRepo = productRepo;
        this.categoriesRepo = categoriesRepo;
    }
//    @GetMapping("/{category}")
//    public ResponseEntity<Object> getProductsByCategory(@PathVariable("category") String category) {
//        Category cat = categoriesRepo.findByName(category);
//        return Util.createResponseEntity(productRepo.findByCategory(cat));
//    }


    @GetMapping("/all")
    public ResponseEntity<Object> getAllProducts() {
        return Util.createResponseEntity(productRepo.findAll());
    }

}
