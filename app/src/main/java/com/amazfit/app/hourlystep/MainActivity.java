package com.amazfit.app.hourlystep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amazfit.app.hourlystep.settings.SettingsActivity;
import com.amazfit.app.stepnotify.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setup activity
        setContentView(R.layout.activity_main);
        //Reset all alarms
        AlarmUtils.cancelAllAlarms(this);
        //Setup alarms for next hour
        AlarmUtils.setupAlarmHour(this);
        AlarmUtils.setupAlarmTenTo(this);
    }

    public void close(View view) {
        finish();
    }

    public void settings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
