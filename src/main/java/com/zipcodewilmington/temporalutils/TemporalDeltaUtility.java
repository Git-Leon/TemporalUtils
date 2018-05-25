package com.zipcodewilmington.temporalutils;

import java.sql.Timestamp;

/**
 * Created by leon on 3/5/18.
 */
public class TemporalDeltaUtility {
    private Long time;
    private TemporalAdapter timeAdapter;

    public TemporalDeltaUtility(Number startTime) {
        this.time = startTime.longValue();
        this.timeAdapter = new TemporalAdapter(startTime.longValue());
    }

    public Long getElapsedTime() {
        return getTimeDifference(System.currentTimeMillis());
    }

    public Long getTimeDifference(Long timeToCompareAgainst) {
        return Math.abs(getTimeRemaining(timeToCompareAgainst));
    }

    public Long getTimeRemaining(Long endTime) {
        return endTime - time;
    }

    public Boolean isTimeRemaining(Long endTime) {
        return getTimeRemaining(endTime) > 0;
    }

    public Boolean isBefore(Long timeToCompareAgainst) {
        TemporalAdapter adapter1 = new TemporalAdapter(timeToCompareAgainst.longValue());
        return adapter1.toLocalDate().isBefore(timeAdapter.toLocalDate());
    }

    public Boolean isAfter(Long timeToCompareAgainst) {
        TemporalAdapter adapter1 = new TemporalAdapter(timeToCompareAgainst.longValue());
        return adapter1.toLocalDate().isAfter(timeAdapter.toLocalDate());
    }


    public Timestamp toTimestamp() {
        return timeAdapter.toTimestamp();
    }

    public String toString() {
        return timeAdapter.toCalendar().toString();
    }
}