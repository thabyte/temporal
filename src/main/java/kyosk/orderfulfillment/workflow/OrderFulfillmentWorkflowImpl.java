package kyosk.orderfulfillment.workflow;

import io.temporal.workflow.Workflow;
import kyosk.orderfulfillment.service.InventoryService;
import kyosk.orderfulfillment.service.ShipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OrderFulfillmentWorkflowImpl implements OrderFulfillmentWorkflow {
    private final Logger logger = LoggerFactory.getLogger(OrderFulfillmentWorkflowImpl.class);
    private final InventoryService inventoryService = Workflow.newActivityStub(InventoryService.class);
    private final ShipmentService shipmentService = Workflow.newActivityStub(ShipmentService.class);

    @Override
    public void processOrder(String orderId) {
        logger.info("Starting workflow for order ID: " + orderId);

        boolean isInStock = inventoryService.checkInventory(orderId);

        if (!isInStock) {
            logger.error("Order ID " + orderId + ": Items out of stock");
            return;
        }

        boolean isShipped = shipmentService.shipOrder(orderId);
        if (!isShipped) {
            logger.error("Order ID : " + orderId + "Shipping Failed");
            return;
        }

        logger.info("Order ID: " + orderId + " fulfilled successfully");
    }
}
