package kyosk.orderfulfillment.config;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;

public class TemporalConnectionTest {
    public static void main(String[] args) {
        WorkflowServiceStubsOptions options = WorkflowServiceStubsOptions.newBuilder()
                .setTarget("localhost:7233")
                .build();

        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(options);
        WorkflowClient client = WorkflowClient.newInstance(service);

        System.out.println("Connected to Temporal service!");
        service.shutdown();
    }
}

