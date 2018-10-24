package com.qt.bwdigital.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.qt.bwdigital.R;

import java.util.ArrayList;
import java.util.List;

public class OthersActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView root = new RecyclerView(this);

        //Add header to a list of settings
        List<BaseSetting> settings = new ArrayList<>();
        settings.add(new HeaderSetting(getString(R.string.other_features)));

        //Setup items
        final SharedPreferences sharedPreferences = getSharedPreferences(getPackageName()+"_settings", Context.MODE_PRIVATE);

        final boolean better_resolution_when_raising_hand = sharedPreferences.getBoolean( "better_resolution_when_raising_hand", getResources().getBoolean(R.bool.better_resolution_when_raising_hand));
        settings.add(new SwitchSetting(null, "Better resolution", "in slpt mode when raising hand", new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPreferences.edit().putBoolean( "better_resolution_when_raising_hand", b).apply();
            }
        }, better_resolution_when_raising_hand));

        final boolean flashing_indicator = sharedPreferences.getBoolean( "flashing_indicator", getResources().getBoolean(R.bool.flashing_indicator));
        settings.add(new SwitchSetting(null, "Flashing \":\"", "Make time's \":\" flashing", new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPreferences.edit().putBoolean( "flashing_indicator", b).apply();
            }
        }, flashing_indicator));

        final boolean month_as_text = sharedPreferences.getBoolean( "month_as_text", getResources().getBoolean(R.bool.month_as_text));
        settings.add(new SwitchSetting(null, "Month as text", "Show month as text", new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPreferences.edit().putBoolean( "month_as_text", b).apply();
            }
        }, month_as_text));

        final boolean three_letters_month_if_text = sharedPreferences.getBoolean( "three_letters_month_if_text", getResources().getBoolean(R.bool.three_letters_month_if_text));
        settings.add(new SwitchSetting(null, "3 letters month", "If text (ex.APR)", new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPreferences.edit().putBoolean( "three_letters_month_if_text", b).apply();
            }
        }, three_letters_month_if_text));

        final boolean three_letters_day_if_text = sharedPreferences.getBoolean( "three_letters_day_if_text", getResources().getBoolean(R.bool.three_letters_day_if_text));
        settings.add(new SwitchSetting(null, "3 letters day", "(ex.MON)", new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPreferences.edit().putBoolean( "three_letters_day_if_text", b).apply();
            }
        }, three_letters_day_if_text));

        final boolean no_0_on_hour_first_digit = sharedPreferences.getBoolean( "no_0_on_hour_first_digit", getResources().getBoolean(R.bool.no_0_on_hour_first_digit));
        settings.add(new SwitchSetting(null, "No 0 in hours", "Remove 0 from hour's first digit", new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPreferences.edit().putBoolean( "no_0_on_hour_first_digit", b).apply();
            }
        }, no_0_on_hour_first_digit));

        final boolean wind_direction_as_arrows = sharedPreferences.getBoolean( "wind_direction_as_arrows", getResources().getBoolean(R.bool.wind_direction_as_arrows));
        settings.add(new SwitchSetting(null, "Wind as arrows", "Wind direction as arrows", new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPreferences.edit().putBoolean( "wind_direction_as_arrows", b).apply();
            }
        }, wind_direction_as_arrows));

        final boolean status_bar = sharedPreferences.getBoolean( "status_bar", getResources().getBoolean(R.bool.status_bar));
        settings.add(new SwitchSetting(null, "Show status bar", "Status bar (charging icon etc)", new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPreferences.edit().putBoolean( "status_bar", b).apply();
            }
        }, status_bar));

        final boolean flashing_heart_rate_icon = sharedPreferences.getBoolean( "flashing_heart_rate_icon", getResources().getBoolean(R.bool.flashing_heart_rate_icon));
        settings.add(new SwitchSetting(null, "Animate heart rate", "Flashing heart rate icon", new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPreferences.edit().putBoolean( "flashing_heart_rate_icon", b).apply();
            }
        }, flashing_heart_rate_icon));

        final int target_calories = sharedPreferences.getInt( "target_calories", 1000);
        settings.add(new SeekbarSetting(null, "Target calories", new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sharedPreferences.edit().putInt( "target_calories", progress).apply();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(seekBar.getContext(), "Target: "+seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        }, target_calories, 3000));

        final int custom_refresh_rate = sharedPreferences.getInt( "custom_refresh_rate", getResources().getInteger(R.integer.custom_refresh_rate)*1000);
        settings.add(new SeekbarSetting(null, "Custom air pressure\nrefresh rate (sec)", new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sharedPreferences.edit().putInt( "custom_refresh_rate", progress).apply();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(seekBar.getContext(), "Refresh rate: "+seekBar.getProgress()+" sec", Toast.LENGTH_SHORT).show();
            }
        }, custom_refresh_rate, 60));

        //Setup layout
        root.setBackgroundResource(R.drawable.settings_background);
        root.setLayoutManager(new LinearLayoutManager(this));
        root.setAdapter(new Adapter(this, settings));
        root.setPadding((int) getResources().getDimension(R.dimen.padding_round_small), 0, (int) getResources().getDimension(R.dimen.padding_round_small), (int) getResources().getDimension(R.dimen.padding_round_large));
        root.setClipToPadding(false);
        setContentView(root);
    }
}
