package kyosk.orderfulfillment.service;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface InventoryService {

    @ActivityMethod
    boolean checkInventory(String orderId);
}