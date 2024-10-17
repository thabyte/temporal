package kyosk.orderfulfillment.repository;

import kyosk.orderfulfillment.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
    Order findOrderById(String id);
}
