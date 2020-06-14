package maestro.config;

import maestro.model.Cart;
import maestro.model.Product;
import maestro.model.User;
import maestro.model.UserContact;
import maestro.repo.ProductRepo;
import maestro.repo.CartRepo;
import maestro.repo.UserContactsRepo;
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
    CartRepo cartRepo;
    @Autowired
    UserContactsRepo userContactsRepo;
    @Autowired
    ProductRepo productRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    private User user;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        cartRepo.deleteAll();
        productRepo.deleteAll();
    }
}