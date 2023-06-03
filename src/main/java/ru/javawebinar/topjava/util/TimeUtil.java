package ru.javawebinar.topjava.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }
}
