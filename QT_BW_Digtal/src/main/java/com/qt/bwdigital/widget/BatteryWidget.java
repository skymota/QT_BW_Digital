package com.qt.bwdigital.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.text.TextPaint;
import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.sport.SlptPowerNumView;
import com.qt.bwdigital.R;
import com.qt.bwdigital.data.Battery;
import com.qt.bwdigital.data.DataType;
import com.qt.bwdigital.resource.ResourceManager;
import com.qt.bwdigital.resource.ResourceManager.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BatteryWidget extends AbstractWidget {
    private int BatteryNum;
    private Boolean batteryBool;
    private Battery batteryData;
    private TextPaint batteryFont;
    private float leftBatteryText;
    private String[] numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String sBattery;
    private float topBatteryText;

    private TextPaint batteryLabelFont;
    private float leftLabelBatteryText;
    private float topLabelBatteryText;

    public void init(Service service) {
        this.leftBatteryText = service.getResources().getDimension(R.dimen.battery_text_left);
        this.topBatteryText = service.getResources().getDimension(R.dimen.battery_text_top);
        this.batteryFont = new TextPaint(1);
        this.batteryFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.TEXT_FONT));
        this.batteryFont.setTextSize(service.getResources().getDimension(R.dimen.battery_font_size));
        this.batteryFont.setColor(service.getResources().getColor(R.color.battery_colour));
        this.batteryFont.setTextAlign(Align.CENTER);
        this.batteryBool = Boolean.valueOf(service.getResources().getBoolean(R.bool.battery));

        this.leftLabelBatteryText = service.getResources().getDimension(R.dimen.battery_label_text_left);
        this.topLabelBatteryText = service.getResources().getDimension(R.dimen.battery_label_text_top);
        this.batteryLabelFont = new TextPaint(1);
        this.batteryLabelFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.TEXT_FONT));
        this.batteryLabelFont.setTextSize(service.getResources().getDimension(R.dimen.battery_font_size));
        this.batteryLabelFont.setColor(service.getResources().getColor(R.color.battery_label_colour));
        this.batteryLabelFont.setTextAlign(Align.CENTER);
    }

    public void onDataUpdate(DataType dataType, Object obj) {
        this.batteryData = (Battery) obj;
    }

    public List<DataType> getDataTypes() {
        return Collections.singletonList(DataType.BATTERY);
    }

    public void draw(Canvas canvas, float f, float f2, float f3, float f4) {
        if (this.batteryData != null && this.batteryBool != null)
        {
            this.sBattery = String.format("%02d%%", new Object[]{Integer.valueOf((this.batteryData.getLevel() * 100) / this.batteryData.getScale())});
            if (this.batteryBool != null)
            {
                canvas.drawText(this.sBattery, this.leftBatteryText, this.topBatteryText, this.batteryFont);

                //Label de la bateria
                canvas.drawText(MainClock.batt[MainClock.language], this.leftLabelBatteryText, this.topLabelBatteryText, this.batteryLabelFont);
            }
        }

    }

    public List<SlptViewComponent> buildSlptViewComponent(Service service) {

        List<SlptViewComponent> slpt_objects = new ArrayList<>();

        SlptPictureView battLabel = new SlptPictureView();
        battLabel.setStringPicture(MainClock.batt[MainClock.language]);
        battLabel.setTextAttr(service.getResources().getDimension(R.dimen.battery_label_font_size), service.getResources().getColor(R.color.battery_label_colour), ResourceManager.getTypeFace(service.getResources(), Font.TEXT_FONT));
        int left = (int) (service.getResources().getDimension(R.dimen.battery_label_text_left) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.battery_label_font_size)));
        int top = (int) (service.getResources().getDimension(R.dimen.battery_label_text_top) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.battery_label_font_size)));

        int width = (int) (MainClock.batt[MainClock.language].length() * 3);
        battLabel.setStart(left - (width/2), top);
        slpt_objects.add(battLabel);

        SlptPictureView slptPictureView = new SlptPictureView();
        slptPictureView.setStringPicture("%");
        SlptLinearLayout slptLinearLayout = new SlptLinearLayout();
        slptLinearLayout.add(new SlptPowerNumView());
        slptLinearLayout.add(slptPictureView);
        slptLinearLayout.setTextAttrForAll(service.getResources().getDimension(R.dimen.battery_font_size), service.getResources().getColor(R.color.battery_colour_slpt), ResourceManager.getTypeFace(service.getResources(), Font.TEXT_FONT));
        slptLinearLayout.alignX = (byte) 2;
        slptLinearLayout.alignY = (byte) 0;
        int dimension = (int) service.getResources().getDimension(R.dimen.battery_text_left);
        if (!service.getResources().getBoolean(R.bool.battery_left_align)) {
            slptLinearLayout.setRect((dimension * 2) + 640, (int) service.getResources().getDimension(R.dimen.battery_font_size));
            dimension = -320;
        }
        slptLinearLayout.setStart(dimension, (int) (service.getResources().getDimension(R.dimen.battery_text_top) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.battery_font_size))));
        if (!service.getResources().getBoolean(R.bool.battery)) {
            slptLinearLayout.show = false;
        }

        slpt_objects.add(slptLinearLayout);

        return slpt_objects;
    }
}
