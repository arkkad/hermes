package maestro.controllers;

import maestro.repo.CategoriesRepo;
import maestro.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriesController {

    private final CategoriesRepo categoriesRepo;

    @Autowired
    public CategoriesController(CategoriesRepo categoriesRepo) {
        this.categoriesRepo = categoriesRepo;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllCats() {
        return Util.createResponseEntity(categoriesRepo.findAll());
    }
}
