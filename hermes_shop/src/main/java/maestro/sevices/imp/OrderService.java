package maestro.sevices.imp;

import maestro.dto.DeliveryDTO;
import maestro.exceptions.EmptyCartException;
import maestro.model.*;
import maestro.repo.OrderRepo;
import maestro.sevices.IOrderService;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toSet;

@Service
public class OrderService implements IOrderService {

    private final UserService userService;
    private final CartService cartService;
    private final OrderRepo orderRepo;

    public OrderService(UserService userService, CartService cartService, OrderRepo orderRepo) {
        this.userService = userService;
        this.cartService = cartService;
        this.orderRepo = orderRepo;
    }

    @Override
    public Order createOrder(String username, DeliveryDTO deliveryDTO, String ccNumber) throws EmptyCartException {
        Cart cart = cartService.getCartOrCreate(username);
        if (cart == null)
            throw new EmptyCartException();
        Order order = new Order.Builder()
                .withDate(new Date())
                .withUser(userService.findByUsername(username))
                .withTotalCost(cart.getItemsCost())
                .withExecution(false)
                .build();

        fillOrderItems(cart, order);
        if (cart.isWithDelivery()) {
            Delivery delivery = createDelivery(deliveryDTO);
            order.setDelivery(delivery);
            order.setDeliveryCost(getDeliveryCost(delivery.getDeliveryAddress()));
        }
        Bill bill = createNewBill(order, ccNumber);
        order.setBill(bill);
        return order;
    }

    private void fillOrderItems(Cart cart, Order order) {
        Set<OrderedProduct> ordered = cart.getCartItems().stream()
                .map(cartItem -> createOrderedProduct(order, cartItem))
                .collect(toSet());
        order.setOrderedProductList(ordered);

    }

    private OrderedProduct createOrderedProduct(Order order, CartItem cartItem) {
        OrderedProduct orderedProduct = new OrderedProduct();
        orderedProduct.setOrder(order);
        orderedProduct.setProduct(cartItem.getProduct());
        return orderedProduct;
    }

    private double getDeliveryCost(String deliveryAddress) {
        return (100 + new Random().nextInt(999 - 100 + 1));
    }


    @Override
    public List<Order> getUserOrders(String username) {
        return orderRepo.findAll();
    }

    @Override
    public void updateStatus(long orderId, boolean executed) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new NoSuchElementException("No such order!"));
        order.setExecuted(executed);
        orderRepo.save(order);
    }

    private Bill createNewBill(Order order, String ccNumber) {
        return new Bill.Builder()
                .witBillDate(new Date())
                .withBillCost(order.getTotalCost())
                .withCcNumber(ccNumber)
                .withOrder(order)
                .withPayed(true)
                .build();
    }

    private Delivery createDelivery(DeliveryDTO deliveryDTO) {
        return new Delivery(
                deliveryDTO.getDeliveryAddress(),
                deliveryDTO.getDeliveryName(),
                deliveryDTO.getDeliveryName());
    }
}
