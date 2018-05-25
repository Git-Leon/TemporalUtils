package com.zipcodewilmington.temporalutils.methodtimer;

import com.zipcodewilmington.temporalutils.TemporalDeltaUtility;

import java.util.function.Consumer;

/**
 * @author leon on 5/25/18.
 */
public class ConsumerTimer<ArgumentType> implements FunctionTimerInterface {
    private final Consumer<ArgumentType> consumer;

    public ConsumerTimer(Consumer<ArgumentType> consumer) {
        this.consumer = consumer;
    }

    public Long invokeAndGetTimeElapsed(ArgumentType argument) {
        TemporalDeltaUtility tdu = new TemporalDeltaUtility(System.currentTimeMillis());
        consumer.accept(argument);
        return tdu.getElapsedTime();
    }

    public static <ArgumentType> Long invokeAndGetTimeElapsed(
            Consumer<ArgumentType>
            consumer, ArgumentType argumentValue) {
        return new ConsumerTimer<>(consumer).invokeAndGetTimeElapsed(argumentValue);
    }
}
