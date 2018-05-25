package com.zipcodewilmington.temporalutils.methodtimer;

import com.zipcodewilmington.temporalutils.TemporalDeltaUtility;

import java.util.function.BiFunction;

/**
 * @author leon on 5/25/18.
 */
public class BiFunctionTimer<FirstArgumentType, SecondArgumentType, ReturnType> implements FunctionTimerInterface {
    private final BiFunction<FirstArgumentType, SecondArgumentType, ReturnType> biFunction;

    public BiFunctionTimer(BiFunction<FirstArgumentType, SecondArgumentType, ReturnType> biFunction) {
        this.biFunction = biFunction;
    }

    public TimeResult<ReturnType> invokeAndGetTimeElapsed(FirstArgumentType arg1, SecondArgumentType arg2) {
        TemporalDeltaUtility tdu = new TemporalDeltaUtility(System.currentTimeMillis());
        ReturnType returnType = biFunction.apply(arg1, arg2);
        return new TimeResult<>(returnType, tdu.getElapsedTime());
    }


    public static <FirstArgumentType, SecondArgumentType, ReturnType> TimeResult<ReturnType> invokeAndGetTimeElapsed(
            BiFunction<FirstArgumentType, SecondArgumentType, ReturnType> biFunction,
            FirstArgumentType arg1,
            SecondArgumentType arg2) {
        return new BiFunctionTimer<>(biFunction).invokeAndGetTimeElapsed(arg1, arg2);
    }
}
