package kyosk.orderfulfillment.service;

import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class PaymentService implements PaymentActivity {
    @Override
    public void processPayment(String orderId) {
        // logic to process payment
        System.out.println("Processing payment for order ID: " + orderId);
    }
}
