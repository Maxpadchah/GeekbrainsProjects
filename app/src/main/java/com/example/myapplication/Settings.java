package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.material.button.MaterialButton;

public class Settings extends AppCompatActivity {
    public static final String KEY_NIGHT_MODE = Settings.class.getCanonicalName() + ".nightMode";
    public static final String MY_PREFIX = Settings.class.getCanonicalName() + ".myPrefix";
    private Switch switchTheme;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences = getSharedPreferences(MY_PREFIX, Context.MODE_PRIVATE);
        switchTheme = findViewById(R.id.switch_them);
        clickValue();
        MaterialButton buttonBack = findViewById(R.id.buttons_back);
        buttonBack.setOnClickListener(v -> {
            finish();
        });
        switchTheme.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                saveValueNight(true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                saveValueNight(false);
            }
            recreate();
        });
    }

    public void saveValueNight(boolean full) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_NIGHT_MODE, full).apply();
    }

    public void clickValue() {
        if (sharedPreferences.getBoolean(KEY_NIGHT_MODE, false)) {
            switchTheme.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            switchTheme.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}