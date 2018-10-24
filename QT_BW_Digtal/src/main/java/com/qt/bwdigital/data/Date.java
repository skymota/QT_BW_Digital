package com.qt.bwdigital.data;

public class Date {
    private final int day;
    private final int month;
    private final int week;
    private final int year;

    public Date(int i, int i2, int i3, int i4) {
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.week = i4;
    }

    public int getYear() {
        return this.year;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDay() {
        return this.day;
    }

    public int getWeek() {
        return this.week;
    }
}
