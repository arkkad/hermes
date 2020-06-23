package maestro.config;

import maestro.model.*;
import maestro.repo.*;
import maestro.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
//@Slf4j
public class DataInitalizer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    UserContactsRepo userContactsRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    private User user;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        cartRepo.deleteAll();
        productRepo.deleteAll();
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleRepo.findByName(Constants.ROLE_USER));
        userRoles.add(roleRepo.findByName(Constants.ROLE_ADMIN));
        UserContact userContact = new UserContact();
        Cart shoppingCart = new Cart();
        User user = new User.Builder()
                .withName("admin")
                .withEmail("123@123.com")
                .withPassword(passwordEncoder.encode("123"))
                .withisEmailVerified(true)
                .withFullName("ADMIN")
                .withRoles(userRoles)
                .withContact(userContact)
                .withCart(shoppingCart)
                .withStatus(Status.ACTIVE)
                .withVerificationCode("1234")
                .build();
        userContact.setUser(user);
        shoppingCart.setUser(user);
        userRepository.save(user);
        cartRepo.save(shoppingCart);

        for (int i = 0; i < 30; i++) {
            Set<String> categorySet = new HashSet<>();
            categorySet.add(Constants.PHONES_CATEGORY);
            Product product = new Product.Builder()
                    .withName("Iphone 11" + i)
                    .withDesc("Iphone")
                    .withPrice(1000)
                    .withStorageCount(10)
                    .withFilename("eaebdbb1-d3ab-4454-be03-e04102ca5e4b.220px-IPhone_6s_vector.svg.png")
                    .withCategories(categorySet)
                    .build();
            productRepo.save(product);
        }
    }
}