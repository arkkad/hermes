package maestro.sevices;

import maestro.model.Cart;

public interface ICartService {
    Cart getCartOrCreate(String userEmail);
    Cart addToCart(String userEmail, String productName, int quantity) throws  maestro.exceptions.UnknownEntityException;
    Cart setDelivery(String userEmail, boolean deliveryIncluded);
    Cart clearCart(String userEmail);
}
