package com.qt.bwdigital.data;

public class WeatherData {
    public String city = "n/a";
    public String humidity = "n/a";
    public String tempFlag;
    public String tempString;
    public String uv = "n/a";
    public int weatherType;
    public String windArrow = "•";
    public String windDirection = "n/a";
    public String windStrength = "n/a";

    public WeatherData(String str, String str2, int i) {
        this.tempFlag = str;
        this.tempString = str2;
        this.weatherType = i;
    }

    public WeatherData(String str, String str2, int i, String str3, String str4, String str5, String str6, String str7) {
        this.tempFlag = str;
        this.tempString = str2;
        this.weatherType = i;
        this.city = str3;
        this.humidity = str4;
        this.uv = str5;
        this.windDirection = str6;
        this.windStrength = str7;
        this.windArrow = getWindDirectionArrow();
    }

    public String toString() {
        return String.format("weather info [tempFlag:%s, tempString:%s, weatherType:%d", new Object[]{this.tempFlag, this.tempString, Integer.valueOf(this.weatherType)});
    }

    public int getWindDirectionType() {
        if (this.windDirection.equals("NW")) {
            return 1;
        }
        if (this.windDirection.equals("N")) {
            return 2;
        }
        if (this.windDirection.equals("NE")) {
            return 3;
        }
        if (this.windDirection.equals("W")) {
            return 4;
        }
        if (this.windDirection.equals("E")) {
            return 5;
        }
        if (this.windDirection.equals("SW")) {
            return 6;
        }
        if (this.windDirection.equals("S")) {
            return 7;
        }
        return this.windDirection.equals("SE") ? 8 : 0;
    }

    public String getWindDirectionArrow() {
        String str = "•";
        if (this.windDirection.equals("NW")) {
            return "↖";
        }
        if (this.windDirection.equals("N")) {
            return "↑";
        }
        if (this.windDirection.equals("NE")) {
            return "↗";
        }
        if (this.windDirection.equals("W")) {
            return "←";
        }
        if (this.windDirection.equals("E")) {
            return "→";
        }
        if (this.windDirection.equals("SW")) {
            return "↙";
        }
        if (this.windDirection.equals("S")) {
            return "↓";
        }
        return this.windDirection.equals("SE") ? "↘" : str;
    }

    public String getUnits() {
        if (!this.tempFlag.equals("1")) {
            if (!this.tempFlag.equals("C")) {
                return "ºF";
            }
        }
        return "ºC";
    }

    public String getTemperature() {
        if (!(this.tempString.isEmpty() || this.weatherType == 22)) {
            if (!this.tempString.equals("0/0")) {
                return this.tempString;
            }
        }
        return "n/a";
    }
}
