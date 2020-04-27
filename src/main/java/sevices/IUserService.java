package sevices;

import model.User;

import java.util.List;

public interface IUserService {
    User findUserById(Long id);

    List<User> getAllUsers();

    boolean saveUser(User user);

    boolean deleteUserById(Long id);
}
