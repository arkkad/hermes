package maestro.controllers;

import maestro.dto.ProductDTO;
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

    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public CartController(CartService shoppingCartService, ProductService productService) {
        this.cartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/getCart")
    public ResponseEntity<Object> getCart(
            @AuthenticationPrincipal User user) {
        return Util.createResponseEntity(cartService.getAllCartItems(user.getUsername()));
    }

    @GetMapping("/getCartItems")
    public ResponseEntity<Object> getCartItemsCount(
            @AuthenticationPrincipal User user) {
        return Util.createResponseEntity(cartService.getCartItemsCount(user.getUsername()));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Object> addToCart(
            @AuthenticationPrincipal User user,
            @RequestBody ProductDTO productDTO) throws UnknownEntityException {
        cartService.addToCart(user.getUsername(), productDTO.getName(), productDTO.getQuantity());
        return Util.createResponseEntity("ok");
    }

    @GetMapping("/deleteProductFromCart/{productName}")
    public ResponseEntity<Object> deteleProductFromCart(
            @AuthenticationPrincipal User user,
            @PathVariable("productName") String productName) {
        try {
            cartService.deleteProductFromCart(user.getUsername(), productName);
            return Util.createResponseEntity(productName + " deleted.");
        } catch (Exception e) {
            return Util.createResponseEntity(e);
        }
    }
}
