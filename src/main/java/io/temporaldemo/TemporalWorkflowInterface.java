package io.temporaldemo;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface TemporalWorkflowInterface {
    @WorkflowMethod
    String run();

    @SignalMethod
    void doCancel();
}
