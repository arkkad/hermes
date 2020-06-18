package maestro.controllers;

import lombok.extern.slf4j.Slf4j;
import maestro.config.sec.jwt.JwtUser;
import maestro.dto.NewUserDTO;
import maestro.dto.ProductDTO;
import maestro.exceptions.UnknownEntityException;
import maestro.model.User;
import maestro.sevices.imp.ProductService;
import maestro.sevices.imp.CartService;
import maestro.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1/shoppingcart")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService shoppingCartService) {
        this.cartService = shoppingCartService;
    }

    @GetMapping("/getCart")
    public ResponseEntity<Object> getCart(
            Authentication authentication) {
        try {
            JwtUser user = (JwtUser) authentication.getPrincipal();
            return Util.createResponseEntity(cartService.getAllCartItems(user.getUsername()));
        } catch (Exception e) {
            log.error("Exception " + e);
            return Util.createResponseEntity(e);
        }
    }

    @GetMapping("/getCartItems")
    public ResponseEntity<Object> getCartItemsCount(
            Authentication authentication) {
        try {
            JwtUser user = (JwtUser) authentication.getPrincipal();
            return Util.createResponseEntity(cartService.getCartItemsCount(user.getUsername()));
        } catch (Exception e) {
            log.error("Exception " + e);
            return Util.createResponseEntity(e);
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Object> addToCart(
            Authentication authentication,
            @RequestBody ProductDTO productDTO) throws UnknownEntityException {
        try {
            JwtUser user = (JwtUser) authentication.getPrincipal();
            cartService.addToCart(user.getUsername(), productDTO.getName(), productDTO.getQuantity());
            return Util.createResponseEntity("Product " + productDTO.getName() + " was successfully added!");
        } catch (Exception e) {
            log.error("Exception " + e);
            return Util.createResponseEntity(e);
        }
    }

    @GetMapping("/deleteProductFromCart/{productName}")
    public ResponseEntity<Object> deteleProductFromCart(
            Authentication authentication,
            @PathVariable("productName") String productName) {
        try {
            JwtUser user = (JwtUser) authentication.getPrincipal();
            cartService.deleteProductFromCart(user.getUsername(), productName);
            return Util.createResponseEntity(productName + " deleted.");
        } catch (Exception e) {
            return Util.createResponseEntity(e);
        }
    }
}
