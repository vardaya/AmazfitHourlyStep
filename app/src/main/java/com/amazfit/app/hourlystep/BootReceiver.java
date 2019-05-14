package com.amazfit.app.hourlystep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Called on boot
        AlarmUtils.cancelAllAlarms(context);
        AlarmUtils.setupAlarmHour(context);
        AlarmUtils.setupAlarmTenTo(context);
    }
}
