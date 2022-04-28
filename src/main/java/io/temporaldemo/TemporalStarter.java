package io.temporaldemo;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;

public class TemporalStarter {
    public static void main(String[] args) {

        // Create the workflow stub
        TemporalWorkflowInterface workflow =
                TemporalWorker.client.newWorkflowStub(
                        TemporalWorkflowInterface.class,
                        WorkflowOptions.newBuilder()
                                .setTaskQueue(TemporalWorker.TASK_QUEUE)
                                .build());

        // start workflow async
        WorkflowClient.start(workflow::run);
        sleep(10);
        workflow.doCancel();
        WorkflowStub untyped = WorkflowStub.fromTyped(workflow);
        String result = untyped.getResult(String.class);

        System.out.println("Result: " + result);
        System.exit(0);
    }


    private static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
