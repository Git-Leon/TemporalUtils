package com.zipcodewilmington.temporalutils.methodtimer;

import java.util.concurrent.TimeUnit;

/**
 * @author leon on 5/25/18.
 */
public class TimeResult<ReturnType> {
    private final ReturnType returnValue;
    private final Long elapsedTime;

    public TimeResult(ReturnType returnType, Long elapsedTime) {
        this.returnValue = returnType;
        this.elapsedTime = elapsedTime;
    }

    public ReturnType getReturnValue() {
        return returnValue;
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public Long getElapsedTime(TimeUnit timeUnit) {
        TimeUnit defaultTimeUnit = TimeUnit.MILLISECONDS;
        long elapsedTimeLong = elapsedTime;
        return defaultTimeUnit.convert(elapsedTimeLong, timeUnit);
    }
}
