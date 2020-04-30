package maestro.sevices;

import maestro.dto.NewUserDTO;
import maestro.model.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    boolean registerNewUser(NewUserDTO newUserDTO);

    User findUserById(UUID id);

    List<User> getAllUsers();

    boolean deleteUserById(UUID id);
}