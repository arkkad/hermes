package maestro.controllers;

import maestro.config.sec.jwt.JwtUser;
import maestro.dto.DeliveryDTO;
import maestro.exceptions.EmptyCartException;
import maestro.sevices.IOrderService;
import maestro.sevices.imp.OrderService;
import maestro.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    private final IOrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/createOrder")
    public ResponseEntity<Object> createOrder(
            Authentication authentication,
            @ModelAttribute DeliveryDTO deliveryDTO,
            @RequestParam(required = false, name = "ccNumber") String ccNumber) throws EmptyCartException {
        JwtUser user = (JwtUser) authentication.getPrincipal();
        return Util.createResponseEntity(orderService.createOrder(user.getUsername(), deliveryDTO, ccNumber));
    }
}
