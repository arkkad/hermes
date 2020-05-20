package maestro.config;

import maestro.model.User;
import maestro.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;

@Component
//@Slf4j
public class DataInitalizer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private User user;

    @Override
    public void run(String... args) throws Exception {
        HashSet<String> strings = new HashSet<>();
//        strings.add("ROLE_ADMIN");
//        userRepository.save(new User.Builder()
//                .withName("admin")
//                .withEmail("123@123.com")
//                .withPassword(passwordEncoder.encode("admin"))
//                .withActive(true)
//                .withisEmailVerified(true)
//                .withFullName("ADMIN")
//                .withDateJoined(LocalDateTime.now(Clock.systemUTC()))
//                .withRoles(strings)
//                .build());
    }
}