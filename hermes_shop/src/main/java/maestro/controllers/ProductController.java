package maestro.controllers;

import maestro.dto.NewProductDTO;
import maestro.model.Product;
import maestro.repo.ProductRepo;
import maestro.sevices.imp.ProductService;
import maestro.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductService productService;

    @Autowired
    public ProductController(ProductRepo productRepo, ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{category}")
    public ResponseEntity<Object> getProductsByCategory(@PathVariable("category") String category) {
        List<Product> cat = productService.findByName(category);
        return Util.createResponseEntity(cat);
    }


    @GetMapping("/all")
    public ResponseEntity<Object> getAllProducts() {
        return Util.createResponseEntity(productService.findAll());
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Object> addProduct(@ModelAttribute NewProductDTO productDTO,
                                             @RequestParam(required = false, name = "file") MultipartFile file) throws IOException {
        String resultFileName = "default.jpeg";
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File fileDir = new File(uploadPath);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            String uuidFileId = UUID.randomUUID().toString();
            resultFileName = uuidFileId + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFileName));
        }
        return Util.createResponseEntity(productService.createProduct(productDTO, resultFileName));
    }

    @GetMapping("delete/{productId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Object> deleteProducts(@PathVariable("productId") String name) {
        try {
            productService.deleteProductByName(name);
            return Util.createResponseEntity("Product deleted");
        } catch (Exception e) {
            return Util.createResponseEntity("Error" + e);
        }
    }


}
