package com.qt.bwdigital.widget;

import android.app.Service;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.text.TextPaint;
import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.sport.SlptTodayStepNumView;
import com.ingenic.iwds.slpt.view.utils.SimpleFile;
import com.qt.bwdigital.R;
import com.qt.bwdigital.data.DataType;
import com.qt.bwdigital.data.Steps;
import com.qt.bwdigital.resource.ResourceManager;
import com.qt.bwdigital.resource.ResourceManager.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StepsWidget extends AbstractWidget {
    private int StepsLevel;
    private int StepsNum;
    private int StepsTargetNum;
    private float leftStepsText;
    private String[] numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String sSteps;
    private Boolean stepsBool;
    private Steps stepsData;
    private TextPaint stepsFont;
    private float topStepsText;

    private TextPaint stepsLabelFont;
    private float leftStepsLabelText;
    private float topStepsLabelText;

    public void init(Service service) {
        this.leftStepsText = service.getResources().getDimension(R.dimen.steps_text_left);
        this.topStepsText = service.getResources().getDimension(R.dimen.steps_text_top);
        this.stepsBool = Boolean.valueOf(service.getResources().getBoolean(R.bool.steps));
        this.stepsFont = new TextPaint(1);
        this.stepsFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.TEXT_FONT));
        this.stepsFont.setTextSize(service.getResources().getDimension(R.dimen.steps_font_size));
        this.stepsFont.setColor(service.getResources().getColor(R.color.steps_colour));
        this.stepsFont.setTextAlign(Align.CENTER);

        this.leftStepsLabelText = service.getResources().getDimension(R.dimen.steps_label_text_left);
        this.topStepsLabelText = service.getResources().getDimension(R.dimen.steps_label_text_top);
        this.stepsLabelFont = new TextPaint(1);
        this.stepsLabelFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.TEXT_FONT));
        this.stepsLabelFont.setTextSize(service.getResources().getDimension(R.dimen.steps_label_font_size));
        this.stepsLabelFont.setColor(service.getResources().getColor(R.color.steps_label_colour));
        this.stepsLabelFont.setTextAlign(Align.CENTER);
    }

    public void onDataUpdate(DataType dataType, Object obj) {
        this.stepsData = (Steps) obj;
    }

    public List<DataType> getDataTypes() {
        return Collections.singletonList(DataType.STEPS);
    }

    public void draw(Canvas canvas, float f, float f2, float f3, float f4) {
        if (this.stepsData != null) {
            this.StepsTargetNum = this.stepsData.getTarget();
            this.StepsNum = this.stepsData.getSteps();
            this.StepsLevel = (int) ((((float) this.StepsNum) / ((float) this.StepsTargetNum)) * 100.0f);
            this.sSteps = String.format("%d", new Object[]{Integer.valueOf(this.StepsNum)});
            if (this.stepsBool.booleanValue()) {
                canvas.drawText(this.sSteps, this.leftStepsText, this.topStepsText, this.stepsFont);

                //Label de las pasos
                canvas.drawText(MainClock.steps[MainClock.language], this.leftStepsLabelText, this.topStepsLabelText, this.stepsLabelFont);
            }
        }
        //Label de las pasos
        canvas.drawText(MainClock.steps[MainClock.language], this.leftStepsLabelText, this.topStepsLabelText, this.stepsLabelFont);
    }

    public List<SlptViewComponent> buildSlptViewComponent(Service service) {

        List<SlptViewComponent> slpt_objects = new ArrayList<>();

        SlptPictureView stepsLabel = new SlptPictureView();
        stepsLabel.setStringPicture(MainClock.steps[MainClock.language]);
        stepsLabel.setTextAttr(service.getResources().getDimension(R.dimen.steps_label_font_size), service.getResources().getColor(R.color.steps_label_colour), ResourceManager.getTypeFace(service.getResources(), Font.TEXT_FONT));
        int left = (int) (service.getResources().getDimension(R.dimen.steps_label_text_left) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.steps_label_font_size)));
        int top = (int) (service.getResources().getDimension(R.dimen.steps_label_text_top) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.steps_label_font_size)));

        int width = (int) (MainClock.steps[MainClock.language].length() * 7);
        stepsLabel.setStart(left - (width/2), top);
        slpt_objects.add(stepsLabel);

        SlptLinearLayout slptLinearLayout = new SlptLinearLayout();
        slptLinearLayout.add(new SlptTodayStepNumView());
        slptLinearLayout.setTextAttrForAll(service.getResources().getDimension(R.dimen.steps_font_size), service.getResources().getColor(R.color.steps_colour_slpt), ResourceManager.getTypeFace(service.getResources(), Font.TEXT_FONT));
        slptLinearLayout.alignX = (byte) 2;
        slptLinearLayout.alignY = (byte) 0;
        int dimension = (int) service.getResources().getDimension(R.dimen.steps_text_left);
        if (!service.getResources().getBoolean(R.bool.steps_left_align)) {
            slptLinearLayout.setRect((dimension * 2) + 640, (int) service.getResources().getDimension(R.dimen.steps_font_size));
            dimension = -320;
        }
        slptLinearLayout.setStart(dimension, (int) (service.getResources().getDimension(R.dimen.steps_text_top) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.steps_font_size))));
        if (!service.getResources().getBoolean(R.bool.steps)) {
            slptLinearLayout.show = false;
        }

        slpt_objects.add(slptLinearLayout);

        return slpt_objects;
    }
}
