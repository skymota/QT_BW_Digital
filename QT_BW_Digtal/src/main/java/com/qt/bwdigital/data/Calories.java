package com.qt.bwdigital.data;

public class Calories {
    private final int calories;

    public Calories(float f) {
        this.calories = Math.round(f);
    }

    public int getCalories() {
        return this.calories;
    }
}
