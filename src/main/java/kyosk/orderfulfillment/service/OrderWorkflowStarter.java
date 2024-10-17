package kyosk.orderfulfillment.service;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import kyosk.orderfulfillment.model.Order;
import kyosk.orderfulfillment.workflow.OrderFulfillmentWorkflow;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderWorkflowStarter {

    private final WorkflowClient workflowClient;

    public OrderWorkflowStarter(WorkflowClient workflowClient) {
        this.workflowClient = workflowClient;
    }

    public void startOrderFulfillmentWorkflow(Order order) {
        WorkflowOptions options = WorkflowOptions.newBuilder().setTaskQueue("ORDER_TASK_QUEUE").build();
        OrderFulfillmentWorkflow workflow = workflowClient.newWorkflowStub(OrderFulfillmentWorkflow.class, options);
        WorkflowClient.start(workflow::processOrder, order.getId());
    }
}
