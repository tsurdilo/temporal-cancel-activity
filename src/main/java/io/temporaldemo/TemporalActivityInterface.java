package io.temporaldemo;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface TemporalActivityInterface {

    // Activity method which can be called during workflow execution
    @ActivityMethod
    String exec();
}
