package com.zipcodewilmington.temporalutils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by leon on 3/5/18.
 */
public class TemporalAdapter {
    private Long time;

    public TemporalAdapter() {
        this(System.currentTimeMillis());
    }

    public TemporalAdapter(Long timeInMilliseconds) {
        this.time = timeInMilliseconds;
    }

    public TemporalAdapter(Timestamp timestamp) {
        this.time = timestamp.getTime();
    }

    public TemporalAdapter(Calendar calendar) {
        this(calendar.getTimeInMillis());
    }

    public TemporalAdapter(Date date) {
        this(date.getTime());
    }

    public TemporalAdapter(LocalTime localTime) {
        this(TimeUnit.NANOSECONDS.toMillis(localTime.toNanoOfDay()));
    }

    public TemporalAdapter(LocalDate localDate) {
        this(localDate.toEpochDay());
    }

    public TemporalAdapter(LocalDateTime localDateTime) {
        this(localDateTime.toLocalDate());
    }

    public TemporalAdapter(String s) {
        this(new SimpleDateFormat(s).getCalendar());
    }

    public TemporalAdapter(Time time) {
        this(time.toLocalTime());
    }
    public Time toTime() {
        return null;
    }

    public String toString() {
        return toCalendar().toString();
    }

    public Timestamp toTimestamp() {
        return new Timestamp(time);
    }

    public Long toNanoseconds() {
        return TimeUnit.NANOSECONDS.convert(time, TimeUnit.MILLISECONDS);
    }

    public LocalTime toLocalTime() {
        return LocalTime.ofNanoOfDay(toNanoseconds());
    }

    public LocalDate toLocalDate() {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = Instant.ofEpochMilli(time);
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.toLocalDate();
    }

    public LocalDateTime toLocalDateTime() {
        return LocalDateTime.ofEpochSecond(time, 0, null);
    }

    public Calendar toCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar;
    }

    public Date toDate() {
        return new Date(time);
    }

}
