package com.zipcodewilmington.temporalutils.methodtimer;

import com.zipcodewilmington.temporalutils.SleepUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author leon on 5/25/18.
 */
public class RunnableTimerTest {


    @Test
    public void testTimeExecutionInstance() {
        // Given
        String expected = "This method should have taken approximately 150 seconds to execute";
        int expectedApproximateTime = 150;

        // When
        RunnableTimer functionTimer = new RunnableTimer(() -> {
            SleepUtils.wait(expectedApproximateTime, TimeUnit.MILLISECONDS);
        });
        Long actualTime = functionTimer.invokeAndGetTimeElapsed();

        // Then
        Assert.assertEquals(expectedApproximateTime, actualTime, 10);
    }

    @Test
    public void testTimeExecutionStatic() {
        // Given
        String expected = "This method should have taken approximately 150 seconds to execute";
        int expectedApproximateTime = 150;

        // When
        Long actualTime = RunnableTimer.invokeAndGetTimeElapsed(() -> {
            SleepUtils.wait(expectedApproximateTime, TimeUnit.MILLISECONDS);
        });

        // Then
        Assert.assertEquals(expectedApproximateTime, actualTime, 10);
    }
}
