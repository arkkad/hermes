package maestro.config.sec;

import lombok.extern.slf4j.Slf4j;
import maestro.config.sec.jwt.JwtUser;
import maestro.config.sec.jwt.JwtUserFactory;
import maestro.model.User;
import maestro.sevices.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username - " + username + " not found!");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}
