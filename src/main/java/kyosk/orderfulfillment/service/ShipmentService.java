package kyosk.orderfulfillment.service;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface ShipmentService {

    @ActivityMethod
    boolean shipOrder(String orderId);
}
