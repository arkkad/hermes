package maestro.sevices;

import maestro.dto.NewUserDTO;
import maestro.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    User registerNewUser(NewUserDTO newUserDTO);

    User findById(Long id);

    List<User> getAllUsers();

    void delete(Long id);

    User findByUsername(String username);
}
