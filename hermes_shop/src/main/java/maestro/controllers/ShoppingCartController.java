package maestro.controllers;

import maestro.exceptions.UnknownEntityException;
import maestro.model.User;
import maestro.sevices.imp.ProductService;
import maestro.sevices.imp.CartService;
import maestro.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/shoppingcart")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShoppingCartController {

    private final CartService shoppingCartService;
    private final ProductService productService;

    @Autowired
    public ShoppingCartController(CartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }


    @PostMapping("/addProduct")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Object> addToCart(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, name = "product") String productName) throws UnknownEntityException {
        shoppingCartService.addToCart(user.getUsername(), productName, 1);
        return Util.createResponseEntity("ok");
    }
}
