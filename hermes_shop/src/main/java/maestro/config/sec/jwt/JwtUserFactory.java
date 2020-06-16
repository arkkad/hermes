package maestro.config.sec.jwt;

import maestro.model.Role;
import maestro.model.Status;
import maestro.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.isEmailVerified(),
                user.getVerificationCode(),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(ArrayList<Role> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
