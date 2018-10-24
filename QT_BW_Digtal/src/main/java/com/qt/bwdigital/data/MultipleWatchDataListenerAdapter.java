package com.qt.bwdigital.data;

import com.huami.watch.watchface.WatchDataListener;

public class MultipleWatchDataListenerAdapter implements WatchDataListener {
    private final MultipleWatchDataListener listener;
    private final DataType type;

    public MultipleWatchDataListenerAdapter(MultipleWatchDataListener multipleWatchDataListener, DataType dataType) {
        this.listener = multipleWatchDataListener;
        this.type = dataType;
    }

    public int getDataType() {
        return this.type.getDataType();
    }

    @Override
    public void onDataUpdate(int i, Object... objects) {
        DataType type = DataType.fromValue(i);
        listener.onDataUpdate(type, type.getValue(objects));

        //Log.w("DinoDevs-GreatFit", "Data Update: "+type.toString()+" = "+type.getValue(objects) );
    }
}
