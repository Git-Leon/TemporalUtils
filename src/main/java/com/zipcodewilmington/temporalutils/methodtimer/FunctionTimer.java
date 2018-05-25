package com.zipcodewilmington.temporalutils.methodtimer;

import com.zipcodewilmington.temporalutils.TemporalDeltaUtility;

import java.util.function.Function;

/**
 * @author leon on 5/25/18.
 */
public class FunctionTimer<ArgumentType, ReturnType> implements FunctionTimerInterface {
    private final Function<ArgumentType, ReturnType> function;

    public FunctionTimer(Function<ArgumentType, ReturnType> function) {
        this.function = function;
    }

    public TimeResult<ReturnType> invokeAndGetTimeElapsed(ArgumentType arg1) {
        TemporalDeltaUtility tdu = new TemporalDeltaUtility(System.currentTimeMillis());
        ReturnType returnType = function.apply(arg1);
        return new TimeResult<ReturnType>(returnType, tdu.getElapsedTime());
    }


    public static <ArgumentType, ReturnType> TimeResult<ReturnType> invokeAndGetTimeElapsed(
            Function<ArgumentType, ReturnType> function,
            ArgumentType arg1) {
        return new FunctionTimer<>(function).invokeAndGetTimeElapsed(arg1);
    }
}
