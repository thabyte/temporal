package kyosk.orderfulfillment.controller;

import kyosk.orderfulfillment.dto.OrderRequest;
import kyosk.orderfulfillment.model.Order;
import kyosk.orderfulfillment.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public String testAPi() {
        return "Welcome to Kyosk Temporal test";
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(
                orderRequest.getCustomerId(),
                orderRequest.getItems(),
                orderRequest.getTotalPrice());
    }
}


