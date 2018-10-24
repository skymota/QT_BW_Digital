package com.qt.bwdigital;

import com.huami.watch.watchface.AbstractSlptClock;
import com.qt.bwdigital.R;
import com.qt.bwdigital.widget.ClockWidget;
import com.qt.bwdigital.widget.Widget;
import java.util.Arrays;
import java.util.LinkedList;

public abstract class AbstractWatchFaceSlpt extends AbstractSlptClock {
    final ClockWidget clock;
    final LinkedList<Widget> widgets = new LinkedList();

    protected AbstractWatchFaceSlpt(ClockWidget clockWidget, Widget... widgetArr) {
        this.clock = clockWidget;
        this.widgets.addAll(Arrays.asList(widgetArr));
    }

    public boolean isClockPeriodSecond() {
        return getResources().getBoolean(R.bool.seconds);
    }
}
