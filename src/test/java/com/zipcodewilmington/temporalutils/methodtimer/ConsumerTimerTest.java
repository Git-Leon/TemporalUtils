package com.zipcodewilmington.temporalutils.methodtimer;

import com.zipcodewilmington.temporalutils.SleepUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author leon on 5/25/18.
 */
public class ConsumerTimerTest {

    @Test
    public void testIfFunctionIsInvoked() {
        // Given
        String stringVal = "This is the value to be printed";
        Long expectedTimeApproximately = 0L;

        // When
        ConsumerTimer<String> consumerTimer = new ConsumerTimer<>(System.out::print);
        Long actualTime = consumerTimer.invokeAndGetTimeElapsed(stringVal);

        // Then
        Assert.assertEquals(expectedTimeApproximately, actualTime);
    }

    @Test
    public void testTimeExecutionInstance() {
        // Given
        String expected = "This method should have taken approximately 150 seconds to execute";
        int expectedApproximateTime = 150;

        // When
        ConsumerTimer<String> functionTimer = new ConsumerTimer<>((a) -> {
            SleepUtils.wait(expectedApproximateTime, TimeUnit.MILLISECONDS);
        });
        Long actualTime = functionTimer.invokeAndGetTimeElapsed(null);

        // Then
        Assert.assertEquals(expectedApproximateTime, actualTime, 10);
    }

    @Test
    public void testTimeExecutionStatic() {
        // Given
        String expected = "This method should have taken approximately 150 seconds to execute";
        int expectedApproximateTime = 150;

        // When
        Long actualTime = ConsumerTimer.invokeAndGetTimeElapsed((a) -> {
            SleepUtils.wait(expectedApproximateTime, TimeUnit.MILLISECONDS);
        }, "");

        // Then
        Assert.assertEquals(expectedApproximateTime, actualTime, 10);
    }
}
