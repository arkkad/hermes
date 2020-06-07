package maestro.sevices;

import maestro.model.ShoppingCart;
import maestro.model.ShoppingCartItem;

import javax.lang.model.UnknownEntityException;
import java.util.List;

public interface IShoppingCartService {
    ShoppingCart getCartOrCreate(String userEmail);
    ShoppingCart addToCart(String userEmail, String productName, int quantity) throws UnknownEntityException, maestro.exceptions.UnknownEntityException;
    ShoppingCart setDelivery(String userEmail, boolean deliveryIncluded);
    ShoppingCart clearCart(String userEmail);
}
