package maestro.hermes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shop")
public class MainController {

    @GetMapping
    private String mainPage(){
        return "mainPage";
    }
}
