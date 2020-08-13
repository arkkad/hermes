package maestro.controllers;

import maestro.dto.NewUserDTO;
import maestro.sevices.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import maestro.sevices.imp.UserService;
import maestro.util.Util;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/users")
public class RegController {

    private final IUserService userService;

    @Autowired
    public RegController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> addUser(@RequestBody NewUserDTO userForm) {
        return Util.createResponseEntity(userService.registerNewUser(userForm));
    }
}
