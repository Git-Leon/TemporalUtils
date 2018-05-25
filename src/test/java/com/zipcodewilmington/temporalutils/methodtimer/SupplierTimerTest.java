package com.zipcodewilmington.temporalutils.methodtimer;

import com.zipcodewilmington.temporalutils.SleepUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author leon on 5/25/18.
 */
public class SupplierTimerTest {

    @Test
    public void testIfFunctionIsInvoked() {
        // Given
        Long expectedTimeApproximately = 0L;

        // When
        SupplierTimer<String> supplierTimer = new SupplierTimer<>(String::new);
        TimeResult<String> actual = supplierTimer.invokeAndGetTimeElapsed();

        // Then
        Assert.assertEquals("", actual.getReturnValue());
        Assert.assertEquals(expectedTimeApproximately, actual.getElapsedTime());
    }

    @Test
    public void testTimeExecutionInstance() {
        // Given
        String expectedReturnValue = "This val should return";
        int expectedApproximateTime = 150;

        // When
        SupplierTimer<String> functionTimer = new SupplierTimer<>(() -> {
            SleepUtils.wait(expectedApproximateTime, TimeUnit.MILLISECONDS);
            return expectedReturnValue;
        });
        TimeResult<String> actual = functionTimer.invokeAndGetTimeElapsed();

        // Then
        Assert.assertEquals(expectedApproximateTime, actual.getElapsedTime(), 10);
        Assert.assertEquals(expectedReturnValue, actual.getReturnValue());
    }

    @Test
    public void testTimeExecutionStatic() {
        // Given
        String expectedReturnValue = "This value should return";
        int expectedApproximateTime = 150;

        // When
        TimeResult<String>  actual = SupplierTimer.invokeAndGetTimeElapsed(() -> {
            SleepUtils.wait(expectedApproximateTime, TimeUnit.MILLISECONDS);
            return expectedReturnValue;
        });

        // Then
        Assert.assertEquals(expectedApproximateTime, actual.getElapsedTime(), 10);
        Assert.assertEquals(expectedReturnValue, actual.getReturnValue());
    }
}
