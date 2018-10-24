package com.qt.bwdigital;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.wearable.watchface.CanvasWatchFaceService.Engine;
import com.qt.bwdigital.R;
import com.qt.bwdigital.data.DataType;
import com.qt.bwdigital.data.MultipleWatchDataListenerAdapter;
import com.qt.bwdigital.widget.AnalogClockWidget;
import com.qt.bwdigital.widget.ClockWidget;
import com.qt.bwdigital.widget.DigitalClockWidget;
import com.qt.bwdigital.widget.Widget;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class AbstractWatchFace extends com.huami.watch.watchface.AbstractWatchFace {
    final ClockWidget clock;
    final LinkedList<Widget> widgets = new LinkedList();

    private class AnalogEngine extends com.huami.watch.watchface.AbstractWatchFace.AnalogEngine {
        private final AnalogClockWidget widget;

        public AnalogEngine(AnalogClockWidget analogClockWidget) {
            super();
            this.widget = analogClockWidget;
        }

        protected void onPrepareResources(Resources resources) {
            this.widget.init(AbstractWatchFace.this);
            for (Widget widget : AbstractWatchFace.this.widgets) {
                widget.init(AbstractWatchFace.this);
                for (DataType type : widget.getDataTypes()) {
                    registerWatchDataListener(new MultipleWatchDataListenerAdapter(widget, type));
                }
            }
            /*
            resources = AbstractWatchFace.this.widgets.iterator();
            while (resources.hasNext()) {
                Widget widget = (Widget) resources.next();
                widget.init(AbstractWatchFace.this);
                for (DataType multipleWatchDataListenerAdapter : widget.getDataTypes()) {
                    registerWatchDataListener(new MultipleWatchDataListenerAdapter(widget, multipleWatchDataListenerAdapter));
                }
            }
            */
        }

        protected void onDrawAnalog(Canvas canvas, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
            Canvas canvas2 = canvas;
            this.widget.onDrawAnalog(canvas2, f, f2, f3, f4, f5, f6, f7);
            Iterator it = AbstractWatchFace.this.widgets.iterator();
            while (it.hasNext()) {
                Widget widget = (Widget) it.next();
                canvas2.translate((float) widget.getX(), (float) widget.getY());
                widget.draw(canvas2, f, f2, f3, f4);
                canvas2.translate((float) (-widget.getX()), (float) (-widget.getY()));
            }
        }
    }

    private class DigitalEngine extends com.huami.watch.watchface.AbstractWatchFace.DigitalEngine {
        private final DigitalClockWidget widget;

        public DigitalEngine(DigitalClockWidget digitalClockWidget) {
            super();
            this.widget = digitalClockWidget;
        }

        protected void onPrepareResources(Resources resources) {
            this.widget.init(AbstractWatchFace.this);
            for (Widget widget : AbstractWatchFace.this.widgets) {
                widget.init(AbstractWatchFace.this);
                for (DataType type : widget.getDataTypes()) {
                    registerWatchDataListener(new MultipleWatchDataListenerAdapter(widget, type));
                }
            }

            /*
            resources = AbstractWatchFace.this.widgets.iterator();
            while (resources.hasNext()) {
                Widget widget = (Widget) resources.next();
                widget.init(AbstractWatchFace.this);
                for (DataType multipleWatchDataListenerAdapter : widget.getDataTypes()) {
                    registerWatchDataListener(new MultipleWatchDataListenerAdapter(widget, multipleWatchDataListenerAdapter));
                }
            }
            */
        }

        protected void onDrawDigital(Canvas canvas, float f, float f2, float f3, float f4, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            Canvas canvas2 = canvas;
            this.widget.onDrawDigital(canvas2, f, f2, f3, f4, i, i2, i3, i4, i5, i6, i7, i8);
            Iterator it = AbstractWatchFace.this.widgets.iterator();
            while (it.hasNext()) {
                Widget widget = (Widget) it.next();
                canvas2.translate((float) widget.getX(), (float) widget.getY());
                widget.draw(canvas2, f, f2, f3, f4);
                canvas2.translate((float) (-widget.getX()), (float) (-widget.getY()));
            }
        }
    }

    protected AbstractWatchFace(ClockWidget clockWidget, Widget... widgetArr) {
        this.clock = clockWidget;
        this.widgets.addAll(Arrays.asList(widgetArr));
    }

    public final Engine onCreateEngine() {
        if (getResources().getBoolean(R.bool.status_bar)) {
            notifyStatusBarPosition(getResources().getDimension(R.dimen.status_left), getResources().getDimension(R.dimen.status_top));
        } else {
            notifyStatusBarPosition(10.0f, 10.0f);
        }
        return AnalogClockWidget.class.isInstance(this.clock) ? new AnalogEngine((AnalogClockWidget) this.clock) : new DigitalEngine((DigitalClockWidget) this.clock);
    }
}
