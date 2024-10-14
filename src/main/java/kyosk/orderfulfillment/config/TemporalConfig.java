package kyosk.orderfulfillment.config;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import kyosk.orderfulfillment.service.InventoryService;
import kyosk.orderfulfillment.service.ShipmentService;
import kyosk.orderfulfillment.workflow.OrderFulfillmentWorkflowImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemporalConfig {
    @Bean
    public WorkflowClient workflowClient() {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        return WorkflowClient.newInstance(service);
    }


    @Bean
    WorkerFactory workerFactory(WorkflowClient workflowClient) {
        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        return factory;
    }

    @Bean
    public Worker orderWorker(WorkerFactory factory, InventoryService inventoryService, ShipmentService shipmentService) {
        Worker worker = factory.newWorker("ORDER_TASK_QUEUE");
        worker.registerWorkflowImplementationTypes(OrderFulfillmentWorkflowImpl.class);
        worker.registerActivitiesImplementations(inventoryService, shipmentService);
        return worker;
    }
}

