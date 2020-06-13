package maestro.sevices.imp;

import maestro.dto.CartItemDTO;
import maestro.dto.ListItemsDTO;
import maestro.dto.NewProductDTO;
import maestro.model.CartItem;
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
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    private final CartRepo cartRepo;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartService(CartRepo shoppingCartRepo, ProductService productService, UserService userService) {
        this.cartRepo = shoppingCartRepo;
        this.productService = productService;
        this.userService = userService;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Cart getCartOrCreate(String username) {
        User user = userService.findByUsername(username);
        Optional<Cart> cartOptional = cartRepo.findByUserId(user.getId());
        return cartOptional.orElseGet(() -> createCart(user));
    }


    private Cart createCart(User user) {
        return cartRepo.save(new Cart(user));
    }

    @Transactional
    @Override
    public void addToCart(String username, String productName, int quantity) throws UnknownEntityException {
        Cart cart = getCartOrCreate(username);
        Product product = productService.getProductByName(productName);
        if (product.isAvailable()) {
            cart.update(product, quantity);
            try {
                cartRepo.save(cart);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @Transactional
    @Override
    public Cart setDelivery(String username, boolean deliveryIncluded) {
        Cart shoppingCart = getCartOrCreate(username);
        shoppingCart.setWithDelivery(deliveryIncluded);
        return cartRepo.save(shoppingCart);
    }

    @Override
    public Cart clearCart(String username) {
        Cart shoppingCart = getCartOrCreate(username);
        shoppingCart.clear();
        return cartRepo.save(shoppingCart);
    }

    @Override
    public ListItemsDTO getAllCartItems(String username) {
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();
        double total;
        double priceTotal = 0;
        User user = userService.findByUsername(username);
        Optional<Cart> cartOptional = cartRepo.findByUserId(user.getId());
        List<CartItem> cartItems = cartOptional.orElseThrow(NoSuchElementException::new).getCartItems();
        for (CartItem item : cartItems) {
            NewProductDTO newProductDTO = new NewProductDTO();
            CartItemDTO cartItemDTO = new CartItemDTO();
            total = item.getProduct().getPrice() * item.getQuantity();
            newProductDTO.setName(item.getProduct().getName());
            newProductDTO.setDescription(item.getProduct().getDescription());
            newProductDTO.setPrice(item.getProduct().getPrice());
            newProductDTO.setStorageCount(item.getProduct().getStorageCount());
            cartItemDTO.setProduct(newProductDTO);
            cartItemDTO.setQuantity(item.getQuantity());
            cartItemDTO.setTotal(total);
            cartItemDTOs.add(cartItemDTO);
            priceTotal += total;
        }
        return new ListItemsDTO(cartItemDTOs, priceTotal);
    }

    @Override
    public void deleteProductFromCart(String username, String productName) {
        Cart cart = getCartOrCreate(username);
        Product product = productService.getProductByName(productName);
        if (product != null) {
            cart.removeItem(productName);
        }
        cartRepo.save(cart);
    }

    @Override
    public int getCartItemsCount(String username) {
        Cart cart = getCartOrCreate(username);
        return cart.getCartItems().size();
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepo.findAll();
    }

    @Override
    public void saveAll(List<Cart> cartList) {
        cartRepo.saveAll(cartList);
    }
}
