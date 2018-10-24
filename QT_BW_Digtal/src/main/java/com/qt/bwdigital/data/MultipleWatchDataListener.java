package com.qt.bwdigital.data;

import java.util.List;

public interface MultipleWatchDataListener {
    List<DataType> getDataTypes();

    void onDataUpdate(DataType dataType, Object obj);
}
