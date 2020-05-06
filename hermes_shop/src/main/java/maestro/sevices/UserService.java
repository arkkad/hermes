package maestro.sevices;

import maestro.dto.NewUserDTO;
import maestro.exceptions.HermesException;
import maestro.model.User;
import maestro.sevices.processing.KafkaMessageProducer;
import maestro.util.Constants;
import maestro.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import maestro.repo.RoleRepository;
import maestro.repo.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UserService implements IUserService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    KafkaMessageProducer kafkaMessageProducer;

    @Override
    public boolean registerNewUser(NewUserDTO newUserDTO) {
        List<User> users = userRepository.findAll();
        Optional<User> userOptional = userRepository.findByEmail(newUserDTO.getEmail());
        User user;
        if (users.size() != 0 && userOptional.isPresent()) {
            user = userOptional.get();
            if (user.isEmailVerified()) {
                throw new HermesException("Such user already exist");
            }
        } else {
            user = new User();
            user.setFullName(newUserDTO.getUsername());
            user.setUsername(newUserDTO.getUsername());
            user.setEmail(newUserDTO.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(newUserDTO.getPassword()));
            user.setDateJoined(DateTimeUtil.getLocalDateTimeUtc());
            user.getRoles().add(roleRepository.findByName(Constants.ROLE_USER));
            user.setEmailVerified(true);

            String verificationCode = generateRandomNumericString();
            user.setVerificationCode(verificationCode);

//            kafkaMessageProducer.sendEmailVerificationCode(user, verificationCode);

            userRepository.save(user);
        }

        return true;
    }

    private String generateRandomNumericString() {
        return String.valueOf(1000 + new Random().nextInt(9999 - 1000 + 1));
    }

    @Override
    public User findUserById(UUID id) {
        Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUserById(UUID id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
