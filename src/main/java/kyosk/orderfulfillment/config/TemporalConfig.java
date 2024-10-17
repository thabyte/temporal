package kyosk.orderfulfillment.config;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import kyosk.orderfulfillment.service.*;
import kyosk.orderfulfillment.workflow.OrderFulfillmentWorkflowImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemporalConfig {

    private final PaymentService paymentService;

    public TemporalConfig(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @Bean
    public WorkflowClient workflowClient() {
        WorkflowServiceStubsOptions options = WorkflowServiceStubsOptions.newBuilder()
                .setTarget("localhost:7233")
                .build();
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(options);
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

    @Bean
    public Worker secondaryWorker(WorkerFactory factory, PaymentService paymentService) {
        Worker worker = factory.newWorker("ORDER_TASK_QUEUE");
        worker.registerActivitiesImplementations(paymentService);
        return worker;
    }
}

