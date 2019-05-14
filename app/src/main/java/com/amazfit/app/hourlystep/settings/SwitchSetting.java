package com.amazfit.app.hourlystep.settings;

import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;


public class SwitchSetting extends BaseSetting {

    //Setting with a change listener, two strings, a state and an icon

    boolean isChecked;
    CompoundButton.OnCheckedChangeListener changeListener;
    String title;
    String subtitle;
    Drawable icon;

    public SwitchSetting(Drawable icon, String title, String subtitle, CompoundButton.OnCheckedChangeListener changeListener, boolean isChecked) {
        this.changeListener = changeListener;
        this.isChecked = isChecked;
        this.title = title;
        this.subtitle = subtitle;
        this.icon = icon;
    }
}
