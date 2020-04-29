package maestro.sevices;

import maestro.dto.NewUserDTO;
import maestro.exceptions.HermesException;
import maestro.model.Role;
import maestro.model.User;
import maestro.util.Constants;
import maestro.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import maestro.repo.RoleRepository;
import maestro.repo.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
public class UserService implements UserDetailsService, IUserService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }

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
            user.setFullName(newUserDTO.getFullName());
            user.setEmail(newUserDTO.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(newUserDTO.getPassword()));
            user.setDateJoined(DateTimeUtil.getLocalDateTimeUtc());

            user.getRoles().add(roleRepository.findByName(Constants.ROLE_USER));
            user.setEmailVerified(true);
            user.setAttemptsCount(0);

            String verificationCode = generateRandomNumericString(Constants.VERIFICATION_CODE_LENGTH);
            user.setVerificationCode(verificationCode);

            userRepository.save(user);
        }

        return false;
    }

    private String generateRandomNumericString(int verificationCodeLength) {
        int random = new Random().nextInt(9999);
        return String.valueOf(random);
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
