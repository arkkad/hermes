package maestro.controllers;

import maestro.config.security.JwtTokenProvider;
import maestro.model.User;
import maestro.repo.UserRepository;
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

import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

/*    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody AuthenticationRequest data) {
        Set<String> roles = new HashSet<>();
        try {
            String username = data.getUsername();
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username" + username + " not found"));
            roles  = this.userRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException("Username " + username + " not found")).getRoles();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
            Map<Object, Object> resp = new HashMap<>();
            resp.put("username", username);
            resp.put("token", token);
            resp.put("isAdmin", roles.contains(Constants.ROLE_ADMIN));
            resp.put("cartItems", user.getCart().getCartItems().size());
            return Util.createResponseEntity(resp);
        } catch (AuthenticationException e) {
            System.out.println(e);
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }*/
}
