package maestro.sevices;

import maestro.dto.NewUserDTO;
import maestro.exceptions.HermesException;
import maestro.model.User;
import maestro.repo.UserRepository;
import maestro.sevices.processing.KafkaMessageProducer;
import maestro.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService implements IUserService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    KafkaMessageProducer kafkaMessageProducer;

    @Override
    public boolean registerNewUser(NewUserDTO newUserDTO) {
        List<User> users = userRepository.findAll();
        Optional<User> userOptional = userRepository.findByEmail(newUserDTO.getEmail());
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (user.isEmailVerified()) {
                throw new HermesException("Such user already exist");
            }
        } else {
            String verificationCode = generateRandomNumericString();
            user = new User.Builder()
                    .withName(newUserDTO.getUsername())
                    .withFullName(newUserDTO.getFullName())
                    .withPassword(bCryptPasswordEncoder.encode(newUserDTO.getPassword()))
                    .withEmail(newUserDTO.getEmail())
                    .withDateJoined(LocalDateTime.now(Clock.systemUTC()))
                    .withVerificationCode(verificationCode)
                    .withActive(true)
                    .withisEmailVerified(true)
                    .withRoles(new HashSet<>(Collections.singletonList(Constants.ROLE_USER)))
                    .build();
//            kafkaMessageProducer.sendEmailVerificationCode(user, verificationCode);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    private String generateRandomNumericString() {
        return String.valueOf(1000 + new Random().nextInt(9999 - 1000 + 1));
    }
}
