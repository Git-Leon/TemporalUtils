package com.zipcodewilmington.temporalutils.methodtimer;

import com.zipcodewilmington.temporalutils.SleepUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author leon on 5/25/18.
 */
public class FunctionTimerTest {

    @Test
    public void testIfFunctionIsInvokedAndReturningValue1() {
        // Given
        String stringVal = "5";
        Integer expected = 5;

        // When
        FunctionTimer<String, Integer> functionTimer = new FunctionTimer<>(Integer::parseInt);
        TimeResult<Integer> actual = functionTimer.invokeAndGetTimeElapsed(stringVal);

        // Then
        Assert.assertEquals(expected, actual.getReturnValue());
    }


    @Test
    public void testIfFunctionIsInvokedAndReturningValue2() {
        // Given
        String baseValue = "The Quick Brown Fox Jumps Over the Lazy Dog";
        String valueToConcatenate = " Quick Brown ";
        String expected = baseValue + valueToConcatenate;

        // When
        FunctionTimer<String, String> functionTimer = new FunctionTimer<>(baseValue::concat);
        TimeResult<String> actual = functionTimer.invokeAndGetTimeElapsed(valueToConcatenate);

        // Then
        Assert.assertEquals(expected, actual.getReturnValue());
    }



    @Test
    public void testTimeExecutionInstance() {
        // Given
        String expected = "This method should have taken approximately 150 seconds to execute";
        int expectedApproximateTime = 150;

        // When
        FunctionTimer<String, String> functionTimer = new FunctionTimer<>((val1) -> {
            SleepUtils.wait(expectedApproximateTime, TimeUnit.MILLISECONDS);
            return expected;
        });
        TimeResult<String> actual = functionTimer.invokeAndGetTimeElapsed(null);

        // Then
        Assert.assertEquals(expectedApproximateTime, actual.getElapsedTime(), 10);
        Assert.assertEquals(expected, actual.getReturnValue());
    }



    @Test
    public void testTimeExecutionStatic() {
        // Given
        String expected = "This method should have taken approximately 150 seconds to execute";
        int expectedApproximateTime = 150;
        // When
        TimeResult<String> actual = FunctionTimer.invokeAndGetTimeElapsed((val1) -> {
            SleepUtils.wait(expectedApproximateTime, TimeUnit.MILLISECONDS);
            return expected;
        }, "");

        // Then
        Assert.assertEquals(expectedApproximateTime, actual.getElapsedTime(), 10);
        Assert.assertEquals(expected, actual.getReturnValue());
    }
}
