package maestro.controllers;

import maestro.dto.ProductNameDTO;
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
public class CartController {

    private final CartService shoppingCartService;
    private final ProductService productService;

    @Autowired
    public CartController(CartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/getCart")
    public ResponseEntity<Object> getCart(
            @AuthenticationPrincipal User user) {
        return Util.createResponseEntity(shoppingCartService.getAllCartItems(user.getUsername()));
    }

    @GetMapping("/getCartItems")
    public ResponseEntity<Object> getCartItemsCount(
            @AuthenticationPrincipal User user) {
        return Util.createResponseEntity(shoppingCartService.getCartItemsCount(user.getUsername()));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Object> addToCart(
            @AuthenticationPrincipal User user,
            @RequestBody ProductNameDTO name) throws UnknownEntityException {
        shoppingCartService.addToCart(user.getUsername(), name.getName(), 2);
        return Util.createResponseEntity("ok");
    }

    @GetMapping("/deleteProductFromCart/{productName}")
    public ResponseEntity<Object> deteleProductFromCart(
            @AuthenticationPrincipal User user,
            @PathVariable("productName") String productName) {
        try {
            shoppingCartService.deleteProductFromCart(user.getUsername(), productName);
            return Util.createResponseEntity(productName + " deleted.");
        } catch (Exception e) {
            return Util.createResponseEntity(e);
        }
    }
}
