package maestro.controllers;

import maestro.config.sec.jwt.JwtTokenProvider;
import maestro.dto.AuthenticationRequestDTO;
import maestro.model.Role;
import maestro.model.User;
import maestro.sevices.IUserService;
import maestro.sevices.imp.UserService;
import maestro.util.Constants;
import maestro.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final IUserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, IUserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> signin(@RequestBody AuthenticationRequestDTO data) {
        List<Role> roles = new ArrayList<>();
        try {
            String username = data.getUsername();
            String password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username - " + user + " not found!");
            }
            roles = user.getRoles();
            String token = jwtTokenProvider.createToken(username, roles);
            Map<Object, Object> resp = new HashMap<>();
            resp.put("username", username);
            resp.put("token", token);
            resp.put("isAdmin", roles.stream().anyMatch(role -> role.getName().equals(Constants.ROLE_ADMIN)));
            resp.put("cartItems", user.getCart().getCartItems().size());
            return Util.createResponseEntity(resp);
        } catch (AuthenticationException e) {
            System.out.println(e);
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
