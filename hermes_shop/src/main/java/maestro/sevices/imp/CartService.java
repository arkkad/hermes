package maestro.sevices.imp;

import maestro.model.Product;
import maestro.model.Cart;
import maestro.model.User;
import maestro.repo.CartRepo;
import maestro.sevices.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.UnknownEntityException;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    private final CartRepo shoppingCartRepo;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartService(CartRepo shoppingCartRepo, ProductService productService, UserService userService) {
        this.shoppingCartRepo = shoppingCartRepo;
        this.productService = productService;
        this.userService = userService;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Cart getCartOrCreate(String username) {
        User user = userService.findByUsername(username);
        Optional<Cart> cartOptional = shoppingCartRepo.findByUserId(user.getId());
        return cartOptional.orElseGet(() -> createCart(user));
    }


    private Cart createCart(User user) {
        return shoppingCartRepo.save(new Cart(user));
    }

    @Transactional
    @Override
    public Cart addToCart(String username, String productName, int quantity) throws UnknownEntityException {
        Cart cart = getCartOrCreate(username);
        Product product = productService.getProductByName(productName);
        if (product.isAvailable()) {
            cart.update(product, quantity);
            try {
                return shoppingCartRepo.save(cart);
            } catch (Exception e) {
                System.out.println(e);
                return cart;
            }
        } else return cart;
    }

    @Transactional
    @Override
    public Cart setDelivery(String username, boolean deliveryIncluded) {
        Cart shoppingCart = getCartOrCreate(username);
        shoppingCart.setWithDelivery(deliveryIncluded);
        return shoppingCartRepo.save(shoppingCart);
    }

    @Override
    public Cart clearCart(String username) {
        Cart shoppingCart = getCartOrCreate(username);
        shoppingCart.clear();
        return shoppingCartRepo.save(shoppingCart);
    }
}
