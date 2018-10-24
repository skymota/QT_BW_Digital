package com.qt.bwdigital.data;

public enum DataType {

    STEPS(1) {
        @Override
        protected <E> E getValue(Object... args) {
            return (E) new Steps((int) args[0], (int) args[1]);
        }
    },
    DISTANCE(2) {
        @Override
        protected <E> E getValue(Object... args) {
            return (E) new TodayDistance((double) args[0]);
        }
    },
    TOTAL_DISTANCE(3) {
        @Override
        protected <E> E getValue(Object... args) {
            return (E) new TotalDistance((double) args[0]);
        }
    },
    CALORIES(4) {
        @Override
        protected <E> E getValue(Object... args) {
            return (E) new Calories((float) args[0]);
        }
    },
    HEART_RATE(5) {
        @Override
        protected <E> E getValue(Object... args) {
            return (E) new HeartRate((int) args[0]);
        }
    },
    DATE(6) {
        @Override
        protected <E> E getValue(Object... args) {
            return (E) new Date((int) args[0], (int) args[1], (int) args[2], (int) args[3]);
        }
    },
    TIME(7) {
        @Override
        protected <E> E getValue(Object... args) {
            return (E) new Time((int) args[0], (int) args[1], (int) args[2], (int) args[3]);
        }
    },

    BATTERY(10) {
        @Override
        protected <E> E getValue(Object... args) {
            return (E) new Battery((int) args[0], (int) args[1]);
        }
    },

    FLOOR(12) {
        @Override
        protected <E> E getValue(Object... args){
            return (E) new TodayFloor ((int) args[0]);
        }

    },

    WEATHER(8) {
        @Override
        protected <E> E getValue(Object... args) {
            return (E) new WeatherData((String) args[0], (String) args[1], ((Integer) args[2]).intValue());
        }
    },

    CUSTOM(13) {
        protected <E> E getValue(Object... args) {
            return (E) new CustomData((String) args[0]);
        }
    },

    ALARM(14) {
        protected <E> E getValue(Object... args) {
            return (E) new Alarm((String) args[0]);
        }
    },

    XDRIP(15) {
        protected <E> E getValue(Object... args) {
            return (E) new Xdrip((String) args[0]);
        }
    };

    private final int dataType;

    private DataType(int dataType) {
        this.dataType = dataType;
    }

    public int getDataType() {
        return this.dataType;
    }

    protected abstract <E> E getValue(Object... args);

    public static final DataType fromValue(int i) {
        for (DataType type : values()) {
            if (type.dataType == i) {
                return type;
            }
        }
        throw new IllegalArgumentException(String.format("Type %s not found", i));
    }
}


/*
public enum DataType {
    STEPS(1) {
        protected <E> E getValue(Object... objArr) {
            return new Steps(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue());
        }
    },
    DISTANCE(2) {
        protected <E> E getValue(Object... objArr) {
            return new TodayDistance(((Double) objArr[0]).doubleValue());
        }
    },
    TOTAL_DISTANCE(3) {
        protected <E> E getValue(Object... objArr) {
            return new TotalDistance(((Double) objArr[0]).doubleValue());
        }
    },
    CALORIES(4) {
        protected <E> E getValue(Object... objArr) {
            return new Calories(((Float) objArr[0]).floatValue());
        }
    },
    HEART_RATE(5) {
        protected <E> E getValue(Object... objArr) {
            return new HeartRate(((Integer) objArr[0]).intValue());
        }
    },
    DATE(6) {
        protected <E> E getValue(Object... objArr) {
            return new Date(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue());
        }
    },
    TIME(7) {
        protected <E> E getValue(Object... objArr) {
            return new Time(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue(), ((Integer) objArr[3]).intValue());
        }
    },
    BATTERY(10) {
        protected <E> E getValue(Object... objArr) {
            return new Battery(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue());
        }
    },
    FLOOR(12) {
        protected <E> E getValue(Object... objArr) {
            return new TodayFloor((float) ((Integer) objArr[0]).intValue());
        }
    },
    WEATHER(8) {
        protected <E> E getValue(Object... objArr) {
            return new WeatherData((String) objArr[0], (String) objArr[1], ((Integer) objArr[2]).intValue());
        }
    };
    
    private final int dataType;

    protected abstract <E> E getValue(Object... objArr);

    private DataType(int i) {
        this.dataType = i;
    }

    public int getDataType() {
        return this.dataType;
    }

    public static final DataType fromValue(int i) {
        for (DataType dataType : values()) {
            if (dataType.dataType == i) {
                return dataType;
            }
        }
        throw new IllegalArgumentException(String.format("Type %s not found", new Object[]{Integer.valueOf(i)}));
    }
}
*/