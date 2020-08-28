package maestro.sevices.imp;

import lombok.extern.slf4j.Slf4j;
import maestro.annotations.Logging;
import maestro.dto.NewUserDTO;
import maestro.exceptions.HermesException;
import maestro.model.Role;
import maestro.model.Status;
import maestro.model.User;
import maestro.repo.RoleRepo;
import maestro.repo.UserRepository;
import maestro.sevices.IUserService;
import maestro.sevices.processing.KafkaMessageProducer;
import maestro.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
@Slf4j
public class UserService implements IUserService {

    @PersistenceContext
    private EntityManager em;
    private final UserRepository userRepository;
    private final RoleRepo roleRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final KafkaMessageProducer kafkaMessageProducer;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepo roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder, KafkaMessageProducer kafkaMessageProducer) {
        this.userRepository = userRepository;
        this.roleRepo = roleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.kafkaMessageProducer = kafkaMessageProducer;
    }

    @Override
    public User registerNewUser(NewUserDTO newUserDTO) {
        Role roleUser = roleRepo.findByName(Constants.ROLE_USER);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
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
                    .withVerificationCode(verificationCode)
                    .withisEmailVerified(true)
                    .withRoles(userRoles)
                    .build();
            user.setStatus(Status.ACTIVE);
            kafkaMessageProducer.sendEmailVerificationCode(user, verificationCode);
            userRepository.save(user);
            log.info("IN register - user: {} successfully registered", user);
            return user;
        }
        return user;
    }

    @Override
    @Logging
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    @Logging
    public List<User> getAllUsers() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    @Logging
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }

    @Override
    @Logging
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", user.get(), username);
        return user.orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }

    private String generateRandomNumericString() {
        return String.valueOf(1000 + new Random().nextInt(9999 - 1000 + 1));
    }
}
