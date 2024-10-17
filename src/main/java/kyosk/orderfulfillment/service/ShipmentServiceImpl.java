package kyosk.orderfulfillment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ShipmentServiceImpl implements ShipmentService {

    private final Logger logger = LoggerFactory.getLogger(ShipmentServiceImpl.class);

    @Override
    public boolean shipOrder(String orderId) {
        // Simulate shipment (replace this with gRPC call to Shipment Service)
        logger.info("Shipping order ID: " + orderId);
        return true;  // Assume shipment is successful
    }
}
