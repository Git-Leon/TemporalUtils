package com.zipcodewilmington.temporalutils.methodtimer;

import com.zipcodewilmington.temporalutils.TemporalDeltaUtility;

/**
 * @author leon on 5/25/18.
 */
public class RunnableTimer implements FunctionTimerInterface {
    private final Runnable runnable;

    public RunnableTimer(Runnable runnable) {
        this.runnable = runnable;
    }

    public Long invokeAndGetTimeElapsed() {
        TemporalDeltaUtility tdu = new TemporalDeltaUtility(System.currentTimeMillis());
        runnable.run();
        return tdu.getElapsedTime();
    }


    public static Long invokeAndGetTimeElapsed(Runnable runnable) {
        return new RunnableTimer(runnable).invokeAndGetTimeElapsed();
    }
}
