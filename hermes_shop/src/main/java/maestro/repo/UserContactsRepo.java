package maestro.repo;

import maestro.model.User;
import maestro.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactsRepo extends JpaRepository<UserContact, Long> {
    User findByUser(User user);
}
