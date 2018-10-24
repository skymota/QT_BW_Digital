package com.qt.bwdigital.widget;

import android.app.Service;
import android.graphics.Canvas;

public abstract class DigitalClockWidget implements ClockWidget {
    public void init(Service service) {
    }

    public abstract void onDrawDigital(Canvas canvas, float f, float f2, float f3, float f4, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);
}
