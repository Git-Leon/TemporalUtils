package com.zipcodewilmington.temporalutils;

import java.util.concurrent.TimeUnit; /**
 * @author leon on 5/25/18.
 */
public class SleepUtils {
    public static void wait(int timeout, TimeUnit timeUnit) {
        try {
            Thread.sleep(timeUnit.convert(timeout, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            throw new Error(e);
        }
    }
}
