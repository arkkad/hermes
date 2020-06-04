package maestro.sevices.imp;

import maestro.model.Product;
import maestro.model.ShoppingCart;
import maestro.model.User;
import maestro.repo.ProductRepo;
import maestro.repo.ShoppingCartRepo;
import maestro.sevices.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.UnknownEntityException;
import java.util.Optional;

@Service
public class ShoppingCartService implements IShoppingCartService {
    private final ShoppingCartRepo shoppingCartRepo;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ShoppingCartService(ShoppingCartRepo shoppingCartRepo, ProductService productService, UserService userService) {
        this.shoppingCartRepo = shoppingCartRepo;
        this.productService = productService;
        this.userService = userService;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ShoppingCart getCartOrCreate(String username) {
        User user = userService.findByUsername(username);
        Optional<ShoppingCart> cartOptional = shoppingCartRepo.findByUserId(user.getId());
        return cartOptional.orElseGet(() -> createCart(user));
    }

    private ShoppingCart createCart(User user) {
        return shoppingCartRepo.save(new ShoppingCart(user));
    }

    @Transactional
    @Override
    public ShoppingCart addToCart(String username, long productId, int quantity) throws UnknownEntityException, maestro.exceptions.UnknownEntityException {
        ShoppingCart shoppingCart = getCartOrCreate(username);
        Product product = productService.getProduct(productId);
        if (product.isAvailable()) {
            shoppingCart.update(product, quantity);
            return shoppingCartRepo.save(shoppingCart);
        } else return shoppingCart;
    }

    @Transactional
    @Override
    public ShoppingCart setDelivery(String username, boolean deliveryIncluded) {
        ShoppingCart shoppingCart = getCartOrCreate(username);
        shoppingCart.setWithDelivery(deliveryIncluded);
        return shoppingCartRepo.save(shoppingCart);
    }

    @Override
    public ShoppingCart clearCart(String username) {
        ShoppingCart shoppingCart = getCartOrCreate(username);
        shoppingCart.clear();
        return shoppingCartRepo.save(shoppingCart);
    }
}
