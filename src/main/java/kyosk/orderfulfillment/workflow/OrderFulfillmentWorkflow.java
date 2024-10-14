package kyosk.orderfulfillment.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderFulfillmentWorkflow {

    @WorkflowMethod
    void processOrder(String orderId);
}
