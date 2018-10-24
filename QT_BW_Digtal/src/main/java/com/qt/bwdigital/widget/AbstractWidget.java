package com.qt.bwdigital.widget;

import android.app.Service;
import android.graphics.drawable.Drawable;
import com.qt.bwdigital.data.DataType;
import com.qt.bwdigital.data.MultipleWatchDataListener;
import java.util.Collections;
import java.util.List;

public abstract class AbstractWidget implements Widget, MultipleWatchDataListener {
    /* renamed from: x */
    private int f70x = 0;
    /* renamed from: y */
    private int f71y = 0;

    public void init(Service service) {
    }

    public void onDataUpdate(DataType dataType, Object obj) {
    }

    public int getX() {
        return this.f70x;
    }

    public int getY() {
        return this.f71y;
    }

    public void setX(int i) {
        this.f70x = i;
    }

    public void setY(int i) {
        this.f71y = i;
    }

    public List<DataType> getDataTypes() {
        return Collections.emptyList();
    }

    protected void setDrawableBounds(Drawable drawable, float x, float y) {
        drawable.setBounds((int) x, (int) y, (int) x + drawable.getMinimumWidth(), (int) y + drawable.getMinimumHeight());
    }
}
