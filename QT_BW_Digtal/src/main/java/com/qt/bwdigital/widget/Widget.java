package com.qt.bwdigital.widget;

import android.app.Service;
import android.graphics.Canvas;
import com.qt.bwdigital.data.MultipleWatchDataListener;

public interface Widget extends MultipleWatchDataListener, HasSlptViewComponent {
    void draw(Canvas canvas, float f, float f2, float f3, float f4);

    int getX();

    int getY();

    void init(Service service);

    void setX(int i);

    void setY(int i);
}
