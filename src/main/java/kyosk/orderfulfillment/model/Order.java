package kyosk.orderfulfillment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private String customerId;
    private List<String> items;
    private Date orderDate;
    private String status;  // Pending, Processing, Completed, Shipped, Delivered
    private Double totalPrice;

    public Order(String id, String customerId, List<String> items, Date orderDate, String status, Double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.orderDate = orderDate;
        this.status = "Pending";
        this.totalPrice = totalPrice;
    }

    public Order(String customerId, List<String> items, Double totalPrice) {
    }
}
