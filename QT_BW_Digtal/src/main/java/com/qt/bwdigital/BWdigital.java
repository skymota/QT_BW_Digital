package com.qt.bwdigital;

import com.huami.watch.watchface.AbstractSlptClock;
import com.qt.bwdigital.settings.LoadSettings;
import com.qt.bwdigital.widget.BatteryWidget;
import com.qt.bwdigital.widget.MainClock;
import com.qt.bwdigital.widget.StepsWidget;

public class BWdigital extends AbstractWatchFace {
    public BWdigital() {
        super(new MainClock(), new BatteryWidget(), new StepsWidget());
    }

    protected Class<? extends AbstractSlptClock> slptClockClass() {
        return BWdigitalSlpt.class;
    }
}
