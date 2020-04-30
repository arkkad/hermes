package maestro.controllers;

import maestro.dto.NewUserDTO;
import maestro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import maestro.sevices.UserService;
import maestro.util.Util;

import javax.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/users")
public class RegController {

    private final UserService userService;

    @Autowired
    public RegController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> addUser(@RequestBody NewUserDTO userForm) {
        return Util.createResponseEntity(userService.registerNewUser(userForm));
    }
}