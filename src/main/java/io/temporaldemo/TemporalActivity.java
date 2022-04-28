package io.temporaldemo;

import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.client.ActivityCompletionException;

public class TemporalActivity implements TemporalActivityInterface {

    @Override
    public String exec() {
        ActivityExecutionContext context = Activity.getExecutionContext();

        for (int i = 0; i < 50; i++) {
            sleep(1);
            try {
                context.heartbeat(i);
            } catch (ActivityCompletionException e) {
                System.out.println("Activity was cancelled..performing some cleanup");
                sleep(2); // simulate some cleanup
                // you can return a result here
//                return "cleanupresult";
                // for sample we just rethrow to show how to handle error in workflow code
                throw e;
            }
        }
        return "normalresult";
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
