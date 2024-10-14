package kyosk.orderfulfillment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Override
    public boolean checkInventory(String orderId) {
        // Simulate inventory check (replace this with gRPC call to Inventory Service)
        logger.info("Checking inventory for order ID: " + orderId);
        return true;  // Assume items are in stock
    }
}
