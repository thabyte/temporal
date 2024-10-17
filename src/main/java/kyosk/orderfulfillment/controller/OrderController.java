package kyosk.orderfulfillment.controller;

import kyosk.orderfulfillment.dto.OrderRequest;
import kyosk.orderfulfillment.model.Order;
import kyosk.orderfulfillment.service.OrderService;
import kyosk.orderfulfillment.service.OrderWorkflowStarter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService orderService;
    private final OrderWorkflowStarter orderFulfillmentService;

    public OrderController(OrderService orderService, OrderWorkflowStarter orderFulfillmentService) {
        this.orderService = orderService;
        this.orderFulfillmentService = orderFulfillmentService;
    }


    @PostMapping
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest.getCustomerId(), orderRequest.getItems(), orderRequest.getTotalPrice());
    }

    @PostMapping("/order/{id}/start")
    public ResponseEntity<String> startOrderFulfillment(@PathVariable String id) {
        Order order = orderService.getOrderById(id);
        orderFulfillmentService.startOrderFulfillmentWorkflow(order);

        return ResponseEntity.ok("Order Fulfillment Workflow started for Order ID: " + id);

    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}


