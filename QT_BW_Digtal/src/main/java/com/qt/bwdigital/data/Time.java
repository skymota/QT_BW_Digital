package com.qt.bwdigital.data;

import java.util.Calendar;

public class Time {
    public int ampm;
    public String ampmStr;
    private String[] defaultAmPmTranslation = new String[]{"am", "pm"};
    public int hours;
    public int minutes;
    public int seconds;
    public String secondsStr;

    public Time(int i, int i2, int i3, int i4) {
        this.seconds = i;
        this.minutes = i2;
        this.hours = i3;
        this.ampm = i4;
        this.secondsStr = getStrSeconds(i);

        // Fix am/pm if not given
        if(this.ampm < 0 || this.ampm > 1){
            Calendar now = Calendar.getInstance();
            this.ampm = (now.get(Calendar.HOUR_OF_DAY) < 12)?0:1;
        }
        this.ampmStr = defaultAmPmTranslation[this.ampm];
    }

    public int getSeconds() {
        return this.seconds;
    }

    public String getStrSeconds(int i) {
        return String.format("%02d", new Object[]{Integer.valueOf(i)});
    }

    public int getMinutes() {
        return this.minutes;
    }

    public int getHours() {
        return this.hours;
    }

    public int getAmpm() {
        return this.ampm;
    }

    public String getAmpmStr(int i) {
        return this.defaultAmPmTranslation[i];
    }
}
