package com.zipcodewilmington.temporalutils.methodtimer;

import com.zipcodewilmington.temporalutils.TemporalDeltaUtility;

import java.util.function.Supplier;

/**
 * @author leon on 5/25/18.
 */
public class SupplierTimer<ReturnType> implements FunctionTimerInterface {
    private final Supplier<ReturnType> supplier;

    public SupplierTimer(Supplier<ReturnType> supplier) {
        this.supplier = supplier;
    }

    public TimeResult<ReturnType> invokeAndGetTimeElapsed() {
        TemporalDeltaUtility tdu = new TemporalDeltaUtility(System.currentTimeMillis());
        ReturnType returnType = supplier.get();
        return new TimeResult<>(returnType, tdu.getElapsedTime());
    }

    public static <ReturnType> TimeResult<ReturnType> invokeAndGetTimeElapsed(Supplier<ReturnType> supplier) {
        TemporalDeltaUtility tdu = new TemporalDeltaUtility(System.currentTimeMillis());
        ReturnType returnType = supplier.get();
        return new TimeResult<>(returnType, tdu.getElapsedTime());
    }
}
