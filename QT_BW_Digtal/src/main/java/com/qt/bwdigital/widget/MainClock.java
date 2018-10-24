package com.qt.bwdigital.widget;

import android.app.Service;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.Log;
import android.widget.Toast;
import com.huami.watch.watchface.util.Util;
import com.ingenic.iwds.slpt.view.core.SlptLinearLayout;
import com.ingenic.iwds.slpt.view.core.SlptPictureView;
import com.ingenic.iwds.slpt.view.core.SlptViewComponent;
import com.ingenic.iwds.slpt.view.digital.SlptDayHView;
import com.ingenic.iwds.slpt.view.digital.SlptDayLView;
import com.ingenic.iwds.slpt.view.digital.SlptHourHView;
import com.ingenic.iwds.slpt.view.digital.SlptHourLView;
import com.ingenic.iwds.slpt.view.digital.SlptMinuteHView;
import com.ingenic.iwds.slpt.view.digital.SlptMinuteLView;
import com.ingenic.iwds.slpt.view.digital.SlptMonthLView;
import com.ingenic.iwds.slpt.view.digital.SlptWeekView;
import com.ingenic.iwds.slpt.view.digital.SlptYear0View;
import com.ingenic.iwds.slpt.view.digital.SlptYear1View;
import com.ingenic.iwds.slpt.view.digital.SlptYear2View;
import com.ingenic.iwds.slpt.view.digital.SlptYear3View;
import com.ingenic.iwds.slpt.view.utils.SimpleFile;
import com.qt.bwdigital.R;
import com.qt.bwdigital.resource.ResourceManager;
import com.qt.bwdigital.resource.ResourceManager.Font;
import com.qt.bwdigital.resource.SlptSecondHView;
import com.qt.bwdigital.resource.SlptSecondLView;
import com.qt.bwdigital.settings.LoadSettings;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainClock extends DigitalClockWidget {
    //public static String[] codes = new String[]{"English", "Čeština", "Deutsch", "Español", "Français", "Hrvatski", "Italian", "Magyar", "Polski", "Português", "Slovenčina", "Türkçe"};
    //private static String[][] days = new String[][]{new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}, new String[]{"Nedele", "Pondeli", "Uteri", "Streda", "Ctvrtek", "Patek", "Sobota"}, new String[]{"Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"}, new String[]{"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"}, new String[]{"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"}, new String[]{"Nedjelja", "Ponedjeljak", "Utorak", "Srijeda", "Cetvrtak", "Petak", "Subota"}, new String[]{"Domenica", "Lunedi", "Martedi", "Mercoledi", "Giovedi", "Venerdi", "Sabato"}, new String[]{"Vasarnap", "Hetfo", "Kedd", "Szerda", "Csutortok", "Pentek", "Szombat"}, new String[]{"Niedziela", "Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek", "Sobota"}, new String[]{"Domingo", "Segunda-feira", "Terca-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sabado"}, new String[]{"Nedela", "Pondelok", "Utorok", "Streda", "Stvrtok", "Piatok", "Sobota"}, new String[]{"Pazar", "Pazartesi", "Sali", "Carsamba", "Persembe", "Cuma", "Cumartesi"}};
    //private static String[][] days_3let = new String[][]{new String[]{"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"}, new String[]{"NED", "PON", "UTE", "STR", "CTV", "PAT", "SOB"}, new String[]{"SON", "MON", "DIE", "MIT", "DON", "FRE", "SAM"}, new String[]{"DOM", "LUN", "MAR", "MIE", "JUE", "VIE", "SAB"}, new String[]{"DIM", "LUN", "MAR", "MER", "JEU", "VEN", "SAM"}, new String[]{"NED", "PON", "UTO", "SRI", "CET", "PET", "SUB"}, new String[]{"DOM", "LUN", "MAR", "MER", "GIO", "VEN", "SAB"}, new String[]{"VAS", "HET", "KED", "SZE", "CSU", "PEN", "SZO"}, new String[]{"NIE", "PON", "WTO", "SRO", "CZW", "PIA", "SOB"}, new String[]{"DOM", "SEG", "TER", "QUA", "QUI", "SEX", "SAB"}, new String[]{"NED", "PON", "UTO", "STR", "STV", "PIA", "SOB"}, new String[]{"PAZ", "PAR", "SAL", "CAR", "PER", "CUM", "CUR"}};
    //private static String[][] months = new String[][]{new String[]{"December", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November"}, new String[]{"Prosinec", "Leden", "Unor", "Brezen", "Duben", "Kveten", "Cerven", "Cervenec", "Srpen", "Zari", "Rijen", "Listopad"}, new String[]{"Dezember", "Januar", "Februar", "Marz", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November"}, new String[]{"Diciembre", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre"}, new String[]{"Decembre", "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre"}, new String[]{"Prosinac", "Sijecanj", "Veljaca", "Ozujak", "Travanj", "Svibanj", "Lipanj", "Srpanj", "Kolovoz", "Rujan", "Listopad", "Studeni"}, new String[]{"Dicembre", "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre"}, new String[]{"December", "Januar", "Februar", "Marcius", "Aprilis", "Majus", "Junius", "Julius", "Augusztus", "Szeptember", "Oktober", "November"}, new String[]{"Grudzien", "Styczen", "Luty", "Marzec", "Kwiecen", "Maj", "Czerwiec", "Lipec", "Sierpien", "Wrzesien", "Pazdziernik", "Listopad"}, new String[]{"Dezembro", "Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro"}, new String[]{"December", "Januar", "Februar", "Marec", "April", "Maj", "Jun", "Jul", "August", "September", "Oktober", "November"}, new String[]{"Aralik", "Ocak", "Subat", "Mart", "Nisan", "Mayis", "Haziran", "Temmuz", "Agustos", "Eylul", "Ekim", "Kasim"}};
    //private static String[][] months_3let = new String[][]{new String[]{"DEC", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV"}, new String[]{"PRO", "LED", "UNO", "BRE", "DUB", "KVE", "CER", "CER", "SRP", "ZAR", "RIJ", "LIS"}, new String[]{"DEZ", "JAN", "FEB", "MAR", "APR", "MAI", "JUN", "JUL", "AUG", "SEP", "OKT", "NOV"}, new String[]{"DIC", "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV"}, new String[]{"DEC", "JAN", "FEV", "MAR", "AVR", "MAI", "JUI", "JUI", "AOU", "SEP", "OCT", "NOV"}, new String[]{"PRO", "SIJ", "VEL", "OZU", "TRA", "SVI", "LIP", "SRP", "KOL", "RUJ", "LIS", "STU"}, new String[]{"DIC", "GEN", "FEB", "MAR", "APR", "MAG", "GIU", "LUG", "AGO", "SET", "OTT", "NOV"}, new String[]{"DEC", "JAN", "FEB", "MAR", "APR", "MAJ", "JUN", "JUL", "AUG", "SZE", "OKT", "NOV"}, new String[]{"GRU", "STY", "LUT", "MAR", "KWI", "MAJ", "CZE", "LIP", "SIE", "WRZ", "PAŹ", "LIS"}, new String[]{"DEZ", "JAN", "FEV", "MAR", "ABR", "MAI", "JUN", "JUL", "AGO", "SET", "OUT", "NOV"}, new String[]{"DEC", "JAN", "FEB", "MAR", "APR", "MAJ", "JUN", "JUL", "AUG", "SEP", "OKT", "NOV"}, new String[]{"ARA", "OCA", "SUB", "MAR", "NIS", "MAY", "HAZ", "TEM", "AGU", "EYL", "EKI", "KAS"}};

    public static String[] codes = new String[]{"English", "Español"};
    private static String[][] days = new String[][]{
            new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"},
            new String[]{"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"}
    };
    private static String[][] days_3let = new String[][]{
            new String[]{"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"},
            new String[]{"DOM", "LUN", "MAR", "MIE", "JUE", "VIE", "SAB"}
    };
    private static String[][] months = new String[][]{
            new String[]{"December", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November"},
            new String[]{"Diciembre", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre"}
    };
    private static String[][] months_3let = new String[][]{
            new String[]{"DEC", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV"},
            new String[]{"DIC", "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV"},
    };
    public static String[] batt = new String[]{"Batt.", "Bat."};
    public static String[] steps = new String[]{"Steps", "Pasos"};

    private Drawable background;
    private boolean dateAlignLeftBool;
    private boolean dateBool;
    private TextPaint dateFont;
    private boolean dayAlignLeftBool;
    private boolean dayBool;
    private TextPaint dayFont;
    private String[] digitalNums = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private TextPaint hourFont;
    private boolean indicatorBool;
    private boolean indicatorFlashBool;
    private TextPaint indicatorFont;
    public static int language = 1;
    private float leftDate;
    private float leftDay;
    private float leftHour;
    private float leftIndicator;
    private float leftMinute;
    private float leftMonth;
    private float leftSeconds;
    private float leftWeekday;
    private float leftYear;
    private TextPaint minutesFont;
    private boolean monthAlignLeftBool;
    private boolean monthBool;
    private TextPaint monthFont;
    private boolean month_as_textBool;
    private boolean secondsBool;
    private TextPaint secondsFont;
    private boolean secondsSlptBool;
    private LoadSettings settings;
    private boolean three_letters_day_if_textBool;
    private boolean three_letters_month_if_textBool;
    private float topDate;
    private float topDay;
    private float topHour;
    private float topIndicator;
    private float topMinute;
    private float topMonth;
    private float topSeconds;
    private float topWeekday;
    private float topYear;
    private boolean weekdayAlignLeftBool;
    private boolean weekdayBool;
    private TextPaint weekdayFont;
    private boolean yearAlignLeftBool;
    private boolean yearBool;
    private TextPaint yearFont;

    private TextPaint labelsFont;
    private float leftLabelBatteryText;
    private float topLabelBatteryText;
    private float leftLabelStepsText;
    private float topLabelStepsText;


    public void init(Service service) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("V1.0 - Code by Marek Zima, style by ");
        stringBuilder.append(service.getResources().getString(R.string.author));
        Toast.makeText(service, stringBuilder.toString(), 0).show();
        this.settings = new LoadSettings(service.getApplicationContext());
        this.language = this.settings.language % codes.length;
        this.background = service.getResources().getDrawable(R.drawable.background);
        this.background.setBounds(0, 0, 320, 320);
        this.leftHour = service.getResources().getDimension(R.dimen.hours_left);
        this.topHour = service.getResources().getDimension(R.dimen.hours_top);
        this.leftMinute = service.getResources().getDimension(R.dimen.minutes_left);
        this.topMinute = service.getResources().getDimension(R.dimen.minutes_top);
        this.leftSeconds = service.getResources().getDimension(R.dimen.seconds_left);
        this.topSeconds = service.getResources().getDimension(R.dimen.seconds_top);
        this.leftIndicator = service.getResources().getDimension(R.dimen.indicator_left);
        this.topIndicator = service.getResources().getDimension(R.dimen.indicator_top);
        this.leftDate = service.getResources().getDimension(R.dimen.date_left);
        this.topDate = service.getResources().getDimension(R.dimen.date_top);
        this.leftDay = service.getResources().getDimension(R.dimen.day_left);
        this.topDay = service.getResources().getDimension(R.dimen.day_top);
        this.leftMonth = service.getResources().getDimension(R.dimen.month_left);
        this.topMonth = service.getResources().getDimension(R.dimen.month_top);
        this.leftWeekday = service.getResources().getDimension(R.dimen.weekday_left);
        this.topWeekday = service.getResources().getDimension(R.dimen.weekday_top);
        this.leftYear = service.getResources().getDimension(R.dimen.year_left);
        this.topYear = service.getResources().getDimension(R.dimen.year_top);
        this.hourFont = new TextPaint(1);
        this.hourFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.CLOCK_FONT));
        this.hourFont.setTextSize(service.getResources().getDimension(R.dimen.hours_font_size));
        this.hourFont.setColor(service.getResources().getColor(R.color.hour_colour));
        this.hourFont.setTextAlign(Align.CENTER);
        this.minutesFont = new TextPaint(1);
        this.minutesFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.CLOCK_FONT));
        this.minutesFont.setTextSize(service.getResources().getDimension(R.dimen.minutes_font_size));
        this.minutesFont.setColor(service.getResources().getColor(R.color.minute_colour));
        this.minutesFont.setTextAlign(Align.CENTER);
        this.secondsBool = service.getResources().getBoolean(R.bool.seconds);
        this.secondsSlptBool = service.getResources().getBoolean(R.bool.seconds_slpt);
        this.secondsFont = new TextPaint(1);
        this.secondsFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.CLOCK_FONT));
        this.secondsFont.setTextSize(service.getResources().getDimension(R.dimen.seconds_font_size));
        this.secondsFont.setColor(service.getResources().getColor(R.color.seconds_colour));
        this.secondsFont.setTextAlign(Align.CENTER);
        this.indicatorBool = service.getResources().getBoolean(R.bool.indicator);
        this.indicatorFlashBool = service.getResources().getBoolean(R.bool.flashing_indicator);
        this.indicatorFont = new TextPaint(1);
        this.indicatorFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.CLOCK_FONT));
        this.indicatorFont.setTextSize(service.getResources().getDimension(R.dimen.indicator_font_size));
        this.indicatorFont.setColor(service.getResources().getColor(R.color.indicator_colour));
        this.indicatorFont.setTextAlign(Align.CENTER);
        this.dateBool = service.getResources().getBoolean(R.bool.date);
        this.dateAlignLeftBool = service.getResources().getBoolean(R.bool.date_left_align);
        this.dateFont = new TextPaint(1);
        this.dateFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.DATE_FONT));
        this.dateFont.setTextSize(service.getResources().getDimension(R.dimen.date_font_size));
        this.dateFont.setColor(service.getResources().getColor(R.color.date_colour));
        this.dateFont.setTextAlign(this.dateAlignLeftBool ? Align.LEFT : Align.CENTER);
        this.weekdayBool = service.getResources().getBoolean(R.bool.week_name);
        this.three_letters_day_if_textBool = service.getResources().getBoolean(R.bool.three_letters_day_if_text);
        this.weekdayAlignLeftBool = service.getResources().getBoolean(R.bool.weekday_left_align);
        this.weekdayFont = new TextPaint(1);
        this.weekdayFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.DATE_FONT));
        this.weekdayFont.setTextSize(service.getResources().getDimension(R.dimen.weekday_font_size));
        this.weekdayFont.setColor(service.getResources().getColor(R.color.weekday_colour));
        this.weekdayFont.setTextAlign(this.weekdayAlignLeftBool ? Align.LEFT : Align.CENTER);
        this.dayBool = service.getResources().getBoolean(R.bool.day);
        this.dayAlignLeftBool = service.getResources().getBoolean(R.bool.day_left_align);
        this.dayFont = new TextPaint(1);
        this.dayFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.DATE_FONT));
        this.dayFont.setTextSize(service.getResources().getDimension(R.dimen.day_font_size));
        this.dayFont.setColor(service.getResources().getColor(R.color.day_colour));
        this.dayFont.setTextAlign(this.dayAlignLeftBool ? Align.LEFT : Align.CENTER);
        this.monthBool = service.getResources().getBoolean(R.bool.month);
        this.month_as_textBool = service.getResources().getBoolean(R.bool.month_as_text);
        this.three_letters_month_if_textBool = service.getResources().getBoolean(R.bool.three_letters_month_if_text);
        this.monthAlignLeftBool = service.getResources().getBoolean(R.bool.month_left_align);
        this.monthFont = new TextPaint(1);
        this.monthFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.DATE_FONT));
        this.monthFont.setTextSize(service.getResources().getDimension(R.dimen.month_font_size));
        this.monthFont.setColor(service.getResources().getColor(R.color.month_colour));
        this.monthFont.setTextAlign(this.monthAlignLeftBool ? Align.LEFT : Align.CENTER);
        this.yearBool = service.getResources().getBoolean(R.bool.year);
        this.yearAlignLeftBool = service.getResources().getBoolean(R.bool.year_left_align);
        this.yearFont = new TextPaint(1);
        this.yearFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.DATE_FONT));
        this.yearFont.setTextSize(service.getResources().getDimension(R.dimen.year_font_size));
        this.yearFont.setColor(service.getResources().getColor(R.color.year_colour));
        this.yearFont.setTextAlign(this.yearAlignLeftBool ? Align.LEFT : Align.CENTER);

        this.labelsFont = new TextPaint(1);
        this.labelsFont.setTypeface(ResourceManager.getTypeFace(service.getResources(), Font.TEXT_FONT));
        this.labelsFont.setTextSize(service.getResources().getDimension(R.dimen.battery_font_size));
        this.labelsFont.setColor(service.getResources().getColor(R.color.battery_colour));
        this.labelsFont.setTextAlign(Align.CENTER);

        this.leftLabelBatteryText = service.getResources().getDimension(R.dimen.battery_label_text_left);
        this.topLabelBatteryText = service.getResources().getDimension(R.dimen.battery_label_text_top);

        this.leftLabelStepsText = service.getResources().getDimension(R.dimen.steps_label_text_left);
        this.topLabelStepsText = service.getResources().getDimension(R.dimen.steps_label_text_top);
    }

    public void onDrawDigital(Canvas canvas, float f, float f2, float f3, float f4, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.background.draw(canvas);
        canvas.drawText(Util.formatTime(i3), this.leftHour, this.topHour, this.hourFont);
        canvas.drawText(Util.formatTime(i2), this.leftMinute, this.topMinute, this.minutesFont);
        if (this.secondsBool) {
            canvas.drawText(String.format("%02d", new Object[]{Integer.valueOf(i)}), this.leftSeconds, this.topSeconds, this.secondsFont);
        }

        // JAVA calendar get/show time library
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, i7);
        if (this.monthBool) {
            String monthText = this.month_as_textBool ? this.three_letters_month_if_textBool ? months_3let[this.language][i5] : months[this.language][i5] : String.format("%02d", new Object[]{Integer.valueOf(i5)});
            canvas.drawText(monthText, this.leftMonth, this.topMonth, this.monthFont);
        }
        if (this.dayBool) {
            canvas.drawText(String.format("%02d", new Object[]{Integer.valueOf(i6)}), this.leftDay, this.topDay, this.dayFont);
        }
        if (this.yearBool) {
            canvas.drawText(String.format("%04d", new Object[]{Integer.valueOf(i4)}), this.leftYear, this.topYear, this.yearFont);
        }
        if (this.weekdayBool) {
            int weekdaynum = calendar.get(Calendar.DAY_OF_WEEK)-1;
            canvas.drawText(this.three_letters_day_if_textBool ? days_3let[this.language][weekdaynum] : days[this.language][weekdaynum], this.leftWeekday, this.topWeekday, this.weekdayFont);
        }
        if (this.indicatorBool) {
            String indicator = ":";
            if (i % 2 == 0 || !this.indicatorFlashBool) {
                canvas.drawText(indicator, this.leftIndicator, this.topIndicator, this.indicatorFont);
            }
        }

    }

    public List<SlptViewComponent> buildSlptViewComponent(Service service) {
        Context context = service;
        this.settings = new LoadSettings(context);
        this.language = this.settings.language;
        this.secondsBool = service.getResources().getBoolean(R.bool.seconds);

//        new SlptPictureView().setImagePicture(Util.assetToBytes(context, "background_slpt.png"));
        Typeface typeFace = ResourceManager.getTypeFace(service.getResources(), Font.CLOCK_FONT);
        SlptLinearLayout slptLinearLayout = new SlptLinearLayout();

        // Draw background image
        SlptPictureView background = new SlptPictureView();
        background.setImagePicture(SimpleFile.readFileFromAssets(service, "background_slpt.png"));
        //slptLinearLayout.add(background);

        slptLinearLayout.add(new SlptHourHView());
        slptLinearLayout.add(new SlptHourLView());
        slptLinearLayout.setStringPictureArrayForAll(this.digitalNums);
        slptLinearLayout.setTextAttrForAll(service.getResources().getDimension(R.dimen.hours_font_size), service.getResources().getColor(R.color.hour_colour_slpt), typeFace);
        slptLinearLayout.alignX = (byte) 2;
        slptLinearLayout.alignY = (byte) 0;
        service.getResources().getDimension(R.dimen.hours_left);
        slptLinearLayout.setRect((int) ((service.getResources().getDimension(R.dimen.hours_left) * 2.0f) + 640.0f), (int) service.getResources().getDimension(R.dimen.hours_font_size));
        slptLinearLayout.setStart(-320, (int) (service.getResources().getDimension(R.dimen.hours_top) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.hours_font_size))));
        SlptLinearLayout slptLinearLayout2 = new SlptLinearLayout();
        slptLinearLayout2.add(new SlptMinuteHView());
        slptLinearLayout2.add(new SlptMinuteLView());
        slptLinearLayout2.setStringPictureArrayForAll(this.digitalNums);
        slptLinearLayout2.setTextAttrForAll(service.getResources().getDimension(R.dimen.minutes_font_size), service.getResources().getColor(R.color.minute_colour_slpt), typeFace);
        slptLinearLayout2.alignX = (byte) 2;
        slptLinearLayout2.alignY = (byte) 0;
        service.getResources().getDimension(R.dimen.minutes_left);
        slptLinearLayout2.setRect((int) ((service.getResources().getDimension(R.dimen.minutes_left) * 2.0f) + 640.0f), (int) service.getResources().getDimension(R.dimen.minutes_font_size));
        slptLinearLayout2.setStart(-320, (int) (service.getResources().getDimension(R.dimen.minutes_top) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.minutes_font_size))));
        SlptLinearLayout slptLinearLayout3 = new SlptLinearLayout();
        SlptPictureView slptPictureView = new SlptPictureView();
        slptPictureView.setStringPicture(":");
        slptLinearLayout3.add(slptPictureView);
        slptLinearLayout3.setTextAttrForAll(service.getResources().getDimension(R.dimen.indicator_font_size), service.getResources().getColor(R.color.indicator_colour_slpt), typeFace);
        slptLinearLayout3.alignX = (byte) 2;
        slptLinearLayout3.alignY = (byte) 0;
        service.getResources().getDimension(R.dimen.indicator_left);
        slptLinearLayout3.setRect((int) ((service.getResources().getDimension(R.dimen.indicator_left) * 2.0f) + 640.0f), (int) service.getResources().getDimension(R.dimen.indicator_font_size));
        slptLinearLayout3.setStart(-320, (int) (service.getResources().getDimension(R.dimen.indicator_top) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.indicator_font_size))));
        if (!service.getResources().getBoolean(R.bool.indicator)) {
            slptLinearLayout3.show = false;
        }
        SlptLinearLayout slptLinearLayout4 = new SlptLinearLayout();
        slptLinearLayout4.add(new SlptSecondHView());
        slptLinearLayout4.add(new SlptSecondLView());
        slptLinearLayout4.setTextAttrForAll(service.getResources().getDimension(R.dimen.seconds_font_size), service.getResources().getColor(R.color.seconds_colour_slpt), typeFace);
        if (!this.secondsSlptBool) {
            slptLinearLayout4.show = false;
        }
        slptLinearLayout4.alignX = (byte) 2;
        slptLinearLayout4.alignY = (byte) 0;
        service.getResources().getDimension(R.dimen.seconds_left);
        slptLinearLayout4.setRect((int) ((service.getResources().getDimension(R.dimen.seconds_left) * 2.0f) + 640.0f), (int) service.getResources().getDimension(R.dimen.seconds_font_size));
        slptLinearLayout4.setStart(-320, (int) (service.getResources().getDimension(R.dimen.seconds_top) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.seconds_font_size))));
        SlptLinearLayout slptLinearLayout5 = new SlptLinearLayout();
        slptLinearLayout5.add(new SlptDayHView());
        slptLinearLayout5.add(new SlptDayLView());
        slptLinearLayout5.setTextAttrForAll(service.getResources().getDimension(R.dimen.day_font_size), service.getResources().getColor(R.color.day_colour_slpt), typeFace);
        slptLinearLayout5.alignX = (byte) 2;
        slptLinearLayout5.alignY = (byte) 0;
        int dimension = (int) service.getResources().getDimension(R.dimen.day_left);
        if (!service.getResources().getBoolean(R.bool.day_left_align)) {
            slptLinearLayout5.setRect((dimension * 2) + 640, (int) service.getResources().getDimension(R.dimen.day_font_size));
            dimension = -320;
        }
        slptLinearLayout5.setStart(dimension, (int) (service.getResources().getDimension(R.dimen.day_top) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.day_font_size))));
        if (!service.getResources().getBoolean(R.bool.day)) {
            slptLinearLayout5.show = false;
        }
        SlptLinearLayout slptLinearLayout6 = new SlptLinearLayout();
        slptLinearLayout6.add(new SlptMonthLView());

        if (Calendar.getInstance().get(Calendar.MONTH) >= 9) {
            months_3let[this.language][2] = months_3let[this.language][0];
            months_3let[this.language][0] = months_3let[this.language][10];
            months_3let[this.language][1] = months_3let[this.language][11];
            months[this.language][2] = months_3let[this.language][0];
            months[this.language][0] = months_3let[this.language][10];
            months[this.language][1] = months_3let[this.language][11];
        }

        if (service.getResources().getBoolean(R.bool.month_as_text)) {
            if (service.getResources().getBoolean(R.bool.three_letters_month_if_text)) {
                slptLinearLayout6.setStringPictureArrayForAll(months_3let[this.language]);
            } else {
                slptLinearLayout6.setStringPictureArrayForAll(months[this.language]);
            }
        }
        slptLinearLayout6.setTextAttrForAll(service.getResources().getDimension(R.dimen.month_font_size), service.getResources().getColor(R.color.month_colour_slpt), typeFace);
        slptLinearLayout6.alignX = (byte) 2;
        slptLinearLayout6.alignY = (byte) 0;
        dimension = (int) service.getResources().getDimension(R.dimen.month_left);
        if (!service.getResources().getBoolean(R.bool.month_left_align)) {
            slptLinearLayout6.setRect((dimension * 2) + 640, (int) service.getResources().getDimension(R.dimen.month_font_size));
            dimension = -320;
        }
        slptLinearLayout6.setStart(dimension, (int) (service.getResources().getDimension(R.dimen.month_top) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.month_font_size))));
        if (!service.getResources().getBoolean(R.bool.month)) {
            slptLinearLayout6.show = false;
        }
        SlptLinearLayout slptLinearLayout7 = new SlptLinearLayout();
        slptLinearLayout7.add(new SlptYear3View());
        slptLinearLayout7.add(new SlptYear2View());
        slptLinearLayout7.add(new SlptYear1View());
        slptLinearLayout7.add(new SlptYear0View());
        slptLinearLayout7.setTextAttrForAll(service.getResources().getDimension(R.dimen.year_font_size), service.getResources().getColor(R.color.year_colour_slpt), typeFace);
        slptLinearLayout7.alignX = (byte) 2;
        slptLinearLayout7.alignY = (byte) 0;
        dimension = (int) service.getResources().getDimension(R.dimen.year_left);
        if (!service.getResources().getBoolean(R.bool.year_left_align)) {
            slptLinearLayout7.setRect((dimension * 2) + 640, (int) service.getResources().getDimension(R.dimen.year_font_size));
            dimension = -320;
        }
        slptLinearLayout7.setStart(dimension, (int) (service.getResources().getDimension(R.dimen.year_top) - ((((float) service.getResources().getInteger(R.integer.font_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.year_font_size))));
        if (!service.getResources().getBoolean(R.bool.year)) {
            slptLinearLayout7.show = false;
        }
        SlptLinearLayout slptLinearLayout8 = new SlptLinearLayout();
        slptLinearLayout8.add(new SlptWeekView());
        if (service.getResources().getBoolean(R.bool.three_letters_day_if_text)) {
            slptLinearLayout8.setStringPictureArrayForAll(days_3let[this.language]);
        } else {
            slptLinearLayout8.setStringPictureArrayForAll(days[this.language]);
        }
        slptLinearLayout8.setTextAttrForAll(service.getResources().getDimension(R.dimen.weekday_font_size), service.getResources().getColor(R.color.weekday_colour_slpt), typeFace);
        slptLinearLayout8.alignX = (byte) 2;
        slptLinearLayout8.alignY = (byte) 0;
        int dimension2 = (int) service.getResources().getDimension(R.dimen.weekday_left);
        if (!service.getResources().getBoolean(R.bool.weekday_left_align)) {
            slptLinearLayout8.setRect((dimension2 * 2) + 640, (int) service.getResources().getDimension(R.dimen.weekday_font_size));
            dimension2 = -320;
        }
        slptLinearLayout8.setStart(dimension2, (int) (service.getResources().getDimension(R.dimen.weekday_top) - ((((float) service.getResources().getInteger(R.integer.weekdayfont_ratio)) / 100.0f) * service.getResources().getDimension(R.dimen.weekday_font_size))));
        int i;
        if (service.getResources().getBoolean(R.bool.week_name)) {
            i = 0;
        } else {
            i = 0;
            slptLinearLayout8.show = false;
        }

        return Arrays.asList(new SlptViewComponent[]{background, slptLinearLayout, slptLinearLayout2, slptLinearLayout3, slptLinearLayout4, slptLinearLayout5, slptLinearLayout6, slptLinearLayout8, slptLinearLayout7});
    }
}
