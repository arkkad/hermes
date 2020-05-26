package maestro.config;

import maestro.model.Category;
import maestro.model.Product;
import maestro.model.User;
import maestro.repo.CategoriesRepo;
import maestro.repo.ProductRepo;
import maestro.repo.UserRepository;
import maestro.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
//@Slf4j
public class DataInitalizer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoriesRepo categoriesRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    private User user;

    @Override
    public void run(String... args) throws Exception {
        HashSet<String> strings = new HashSet<>();
        strings.add("ROLE_ADMIN");
        userRepository.save(new User.Builder()
                .withName("admin")
                .withEmail("123@123.com")
                .withPassword(passwordEncoder.encode("admin"))
                .withActive(true)
                .withisEmailVerified(true)
                .withFullName("ADMIN")
                .withDateJoined(LocalDateTime.now(Clock.systemUTC()))
                .withRoles(strings)
                .build());

        Set<Category> categorySet = new HashSet<>();
        Category category = categoriesRepo.findByName(Constants.PHONES_CATEGORY);
        categorySet.add(category);
        Product product = new Product.Builder()
                .withName("Iphone 11")
                .withDesc("Iphone")
                .withPrice(1000)
                .withStorageCount(10)
                .withCategories(categorySet)
                .build();
        categoriesRepo.save(category);
        productRepo.save(product);
    }
}