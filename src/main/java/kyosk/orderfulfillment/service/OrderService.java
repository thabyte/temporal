package kyosk.orderfulfillment.service;

import kyosk.orderfulfillment.model.Order;
import kyosk.orderfulfillment.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final OrderWorkflowStarter orderWorkflowStarter;

    public OrderService(OrderRepository orderRepository, OrderWorkflowStarter orderWorkflowStarter) {
        this.orderRepository = orderRepository;
        this.orderWorkflowStarter = orderWorkflowStarter;
    }


    public Order createOrder(String customerId, List<String> items, Double totalPrice) {
        Order order = new Order(customerId, items, totalPrice);
        order = orderRepository.save(order);  // Save order to MongoDB

        orderWorkflowStarter.startOrderFulfillmentWorkflow(order);

        return order;
    }
}
