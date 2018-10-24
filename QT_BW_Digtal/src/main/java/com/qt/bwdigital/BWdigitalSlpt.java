package com.qt.bwdigital;

import android.content.Context;
import android.util.Log;

import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.core.SlptAbsoluteLayout;
import com.ingenic.iwds.slpt.view.core.SlptLayout;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.qt.bwdigital.widget.BatteryWidget;
import com.qt.bwdigital.widget.MainClock;
import com.qt.bwdigital.widget.StepsWidget;
import com.qt.bwdigital.widget.Widget;
import java.util.Iterator;

public class BWdigitalSlpt extends AbstractWatchFaceSlpt {
    public BWdigitalSlpt() {
        super(new MainClock(), new BatteryWidget(), new StepsWidget());
    }

    protected SlptLayout createClockLayout26WC() {
        SlptLayout slptAbsoluteLayout = new SlptAbsoluteLayout();
        for (SlptViewComponent add : this.clock.buildSlptViewComponent(this)) {
            slptAbsoluteLayout.add(add);
        }
        Iterator it = this.widgets.iterator();
        while (it.hasNext()) {
            for (SlptViewComponent add2 : ((Widget) it.next()).buildSlptViewComponent(this)) {
                slptAbsoluteLayout.add(add2);
            }
        }
        return slptAbsoluteLayout;
    }

    protected SlptLayout createClockLayout8C() {
        SlptLayout slptAbsoluteLayout = new SlptAbsoluteLayout();
        for (SlptViewComponent add : this.clock.buildSlptViewComponent(this)) {
            slptAbsoluteLayout.add(add);
        }
        Iterator it = this.widgets.iterator();
        while (it.hasNext()) {
            for (SlptViewComponent add2 : ((Widget) it.next()).buildSlptViewComponent(this)) {
                slptAbsoluteLayout.add(add2);
            }
        }
        return slptAbsoluteLayout;
    }

    protected void initWatchFaceConfig() {
        Log.w("MarekZima", "Initiating watchface");

        //this.getResources().getBoolean(R.bool.seconds)

        Context context = this.getApplicationContext();
        boolean needRefreshSecond = Util.needSlptRefreshSecond(context);
        if (needRefreshSecond) {
            this.setClockPeriodSecond(true);
        }
    }

}
