package maestro.sevices;

import maestro.exceptions.EmptyCartException;
import maestro.model.Delivery;
import maestro.model.Order;

import java.util.List;

public interface IOrderService {

    Order createOrder(String username, double deliveryCost, Delivery delivery, String ccNumber) throws EmptyCartException;
    List<Order> getUserOrders(String username);
    void updateStatus(long orderId, boolean executed);
}
