package com.group.libraryapp.domain;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Day {
    private String dayOfTheWeek;

    public Day(LocalDate date) {
        this.dayOfTheWeek = date.getDayOfWeek()
                        .getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                        .toUpperCase();
    }

    // 왜 getter가 있어야 되지??
    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }
}
