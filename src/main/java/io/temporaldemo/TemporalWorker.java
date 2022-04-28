package io.temporaldemo;

import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.client.WorkflowClient;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class TemporalWorker {

    public static final WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    public static final WorkflowClient client = WorkflowClient.newInstance(service);
    public static final String TASK_QUEUE = "TemporalTaskQueue";

    public static void main(String[] args) {

        // Create worker factory and a worker
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TASK_QUEUE);

        // Register our workflow and activity with worker
        worker.registerWorkflowImplementationTypes(TemporalWorkflow.class);
        worker.registerActivitiesImplementations(new TemporalActivity());

        factory.start();
    }
}