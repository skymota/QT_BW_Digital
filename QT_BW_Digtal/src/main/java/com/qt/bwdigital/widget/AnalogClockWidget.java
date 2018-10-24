package com.qt.bwdigital.widget;

import android.app.Service;
import android.graphics.Canvas;

public abstract class AnalogClockWidget implements ClockWidget {
    public void init(Service service) {
    }

    public abstract void onDrawAnalog(Canvas canvas, float f, float f2, float f3, float f4, float f5, float f6, float f7);
}
