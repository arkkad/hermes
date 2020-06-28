package maestro.sevices;

import maestro.dto.DeliveryDTO;
import maestro.exceptions.EmptyCartException;
import maestro.model.Delivery;
import maestro.model.Order;

import java.util.List;

public interface IOrderService {

    Order createOrder(String username, DeliveryDTO delivery, String ccNumber) throws EmptyCartException;
    List<Order> getUserOrders(String username);
    void updateStatus(long orderId, boolean executed);
}
