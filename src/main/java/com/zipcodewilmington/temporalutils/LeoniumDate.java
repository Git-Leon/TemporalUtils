package com.zipcodewilmington.temporalutils;

import java.time.LocalDate;

/**
 * Created by leon on 3/5/18.
 */
public class LeoniumDate {
    private final LocalDate localDate;
    private TemporalAdapter temporalAdapter;
    public LeoniumDate(Long time) {
        this.temporalAdapter = new TemporalAdapter(time);
        this.localDate = temporalAdapter.toLocalDate();
    }

    public Integer getMonthOfYear() {
        return localDate.getMonthValue();
    }

    public Integer getDayOfMonth() {
        return localDate.getDayOfMonth();
    }

    public Integer getYearOfDate() {
        return localDate.getYear();
    }
}
