package com.amazfit.app.hourlystep.settings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.amazfit.app.stepnotify.R;

import java.util.List;

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final Context context;
    private final List<BaseSetting> settings;

    public Adapter(Context context, List<BaseSetting> settings){
        this.context = context;
        this.settings = settings;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Three layouts possible - Header, icon and switch
        if (viewType == 0) {
            //Header
            return new ViewHolder(layoutInflater.inflate(R.layout.item_header, parent, false));
        } else if (viewType == 1) {
            //Icon Item
            return new ViewHolder(layoutInflater.inflate(R.layout.item_preference_icon, parent, false));
        } else if (viewType == 2) {
            //Switch Item
            return new ViewHolder(layoutInflater.inflate(R.layout.item_preference_switch, parent, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        //Return the type of a given item
        BaseSetting setting = settings.get(position);
        if (setting instanceof HeaderSetting) return 0;
        if (setting instanceof IconSetting) return 1;
        else return 2;
    }


    @Override
    public void onBindViewHolder(final Adapter.ViewHolder holder, int position) {
        //Get base setting for position
        BaseSetting setting = settings.get(position);
        if (setting instanceof HeaderSetting) {
            //Header, just set text
            holder.title.setText(((HeaderSetting) setting).title);
        } else {
            if (setting instanceof SwitchSetting) {
                //Switch, setup the change listener and click listener for the root view
                SwitchSetting switchSetting = (SwitchSetting) setting;
                holder.sw.setOnCheckedChangeListener(switchSetting.changeListener);
                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.sw.toggle();
                    }
                });
                //Set default check
                holder.sw.setChecked(switchSetting.isChecked);
                //Setup title
                holder.title.setText(switchSetting.title);
                //Setup subtitle if required
                if (switchSetting.subtitle != null) {
                    holder.subtitle.setText(switchSetting.subtitle);
                    holder.subtitle.setVisibility(View.VISIBLE);
                } else {
                    holder.subtitle.setText("");
                    holder.subtitle.setVisibility(View.GONE);
                }
            } else {
                //Icon, setup icon, click listener and title
                IconSetting iconSetting = (IconSetting) setting;
                holder.icon.setImageDrawable(iconSetting.icon);
                holder.root.setOnClickListener(iconSetting.onClickListener);
                holder.title.setText(iconSetting.title);
                //Setup subtitle if required
                if (iconSetting.subtitle != null) {
                    holder.subtitle.setText(iconSetting.subtitle);
                    holder.subtitle.setVisibility(View.VISIBLE);
                } else {
                    holder.subtitle.setText("");
                    holder.subtitle.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return settings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;
        TextView title, subtitle;
        ImageView icon;
        Switch sw;

        public ViewHolder(View itemView) {
            super(itemView);
            //Set views
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
            icon = itemView.findViewById(R.id.icon);
            sw = itemView.findViewById(R.id.sw);
            root = itemView;
        }
    }
}