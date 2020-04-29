package maestro.controllers;

import maestro.dto.NewUserDTO;
import maestro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import maestro.sevices.UserService;
import maestro.util.Util;

import javax.validation.Valid;

@RestController
public class RegController {

    private final UserService userService;

    @Autowired
    public RegController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> addUser(@ModelAttribute("userForm") @Valid NewUserDTO userForm) {
        return Util.createResponseEntity(userService.registerNewUser(userForm));
    }
}
