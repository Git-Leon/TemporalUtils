package com.zipcodewilmington.temporalutils;

import java.sql.Timestamp;

/**
 * Created by leon on 3/5/18.
 */
public class TemporalUtility {
    private long time;
    private TemporalAdapter timeAdapter;

    public TemporalUtility(Long time) {
        this.time = time;
        this.timeAdapter = new TemporalAdapter(time);
    }

    public Long getTimeDifference(Long timeToCompareAgainst) {
        long timeDelta = time - timeToCompareAgainst;
        return Math.abs(timeDelta);
    }

    public Long timeRemaining(Long endTime) {
        Long startTime = time;
        return endTime - startTime;
    }

    public Boolean isBefore(Long timeToCompareAgainst) {
        TemporalAdapter adapter1 = new TemporalAdapter(timeToCompareAgainst);
        return adapter1.toLocalDate().isBefore(timeAdapter.toLocalDate());
    }

    public Boolean isAfter(Long timeToCompareAgainst) {
        TemporalAdapter adapter1 = new TemporalAdapter(timeToCompareAgainst);
        return adapter1.toLocalDate().isAfter(timeAdapter.toLocalDate());
    }


    public Timestamp toTimestamp() {
        return timeAdapter.toTimestamp();
    }

    public String toString() {
        return timeAdapter.toCalendar().toString();
    }
}