package com.zipcodewilmington.temporalutils.methodtimer;

import com.zipcodewilmington.temporalutils.SleepUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author leon on 5/25/18.
 */
public class BiFunctionTimerTest {

    @Test
    public void testIfFunctionIsInvokedAndReturningValue1() {
        // Given
        String stringVal = "F";
        Integer radix = 16;
        Integer expected = 15;

        // When
        BiFunctionTimer<String, Integer, Integer> biFunctionTimer = new BiFunctionTimer<>(Integer::parseInt);
        TimeResult<Integer> actual = biFunctionTimer.invokeAndGetTimeElapsed(stringVal, radix);

        // Then
        Assert.assertEquals(expected, actual.getReturnValue());
    }


    @Test
    public void testIfFunctionIsInvokedAndReturningValue2() {
        // Given
        String stringVal = "The Quick Brown Fox Jumps Over the Lazy Dog";
        String target = " Quick Brown ";
        String replacement = "";
        String expected = "TheFox Jumps Over the Lazy Dog";

        // When
        BiFunctionTimer<String, String, String> biFunctionTimer = new BiFunctionTimer<>(stringVal::replaceAll);
        TimeResult<String> actual = biFunctionTimer.invokeAndGetTimeElapsed(target, replacement);

        // Then
        Assert.assertEquals(expected, actual.getReturnValue());
    }



    @Test
    public void testTimeExecutionInstanceMethod() {
        // Given
        String expected = "This method should have taken approximately 1500 seconds to execute";
        int expectedApproximateTime = 1500;

        // When
        BiFunctionTimer<String, String, String> biFunctionTimer = new BiFunctionTimer<>((val1, val2) -> {
            SleepUtils.wait(expectedApproximateTime, TimeUnit.MILLISECONDS);
            return expected;
        });
        TimeResult<String> actual = biFunctionTimer.invokeAndGetTimeElapsed(null, null);

        // Then
        Assert.assertEquals(expectedApproximateTime, actual.getElapsedTime(), 10);
        Assert.assertEquals(expected, actual.getReturnValue());
    }


    @Test
    public void testTimeExecutionStaticMethod() {
        // Given
        String expected = "This method should have taken approximately 1500 seconds to execute";
        int expectedApproximateTime = 1500;

        // When
        TimeResult<String> actual = BiFunctionTimer.invokeAndGetTimeElapsed((val1, val2) -> {
            SleepUtils.wait(expectedApproximateTime, TimeUnit.MILLISECONDS);
            return expected;
        }, "FirstArgument", "SecondArgument");

        // Then
        Assert.assertEquals(expectedApproximateTime, actual.getElapsedTime(), 10);
        Assert.assertEquals(expected, actual.getReturnValue());
    }

}