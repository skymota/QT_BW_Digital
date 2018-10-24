package com.qt.bwdigital.data;

public class Battery {
    private final int level;
    private final int scale;

    public Battery(int i, int i2) {
        this.level = i;
        this.scale = i2;
    }

    public int getLevel() {
        return this.level;
    }

    public int getScale() {
        return this.scale;
    }
}
