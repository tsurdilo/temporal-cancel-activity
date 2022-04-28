package io.temporaldemo;

import io.temporal.activity.ActivityCancellationType;
import io.temporal.activity.ActivityOptions;
import io.temporal.failure.ActivityFailure;
import io.temporal.failure.ApplicationFailure;
import io.temporal.workflow.Async;
import io.temporal.workflow.CancellationScope;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class TemporalWorkflow implements TemporalWorkflowInterface {
    private CancellationScope activityScope;
    private Promise<String> activityPromise;

    private final TemporalActivityInterface activity =
        Workflow.newActivityStub(
            TemporalActivityInterface.class,
            ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofMinutes(1))
                     // Wait for the Activity Execution to confirm any requested cancellation
                    .setCancellationType(ActivityCancellationType.WAIT_CANCELLATION_COMPLETED)
                    .build());

    @Override
    public String run() {
        activityScope =
                Workflow.newCancellationScope(
                        () -> activityPromise = Async.function(activity::exec)
                );

        // start cancellation scope
        activityScope.run();

        try {
            return activityPromise.get();
        } catch (ActivityFailure e) {
            System.out.println("Activity Failure: " + e.getOriginalMessage());
            return "canceledresults";
        }
    }

    @Override
    public void doCancel() {
        // you can cancel scope from signal method
        activityScope.cancel();
    }
}
