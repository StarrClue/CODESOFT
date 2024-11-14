package com.example.alarmclock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class AlarmListAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> alarms;

    public AlarmListAdapter(Context context, ArrayList<String> alarms) {
        super(context, 0, alarms);
        this.context = context;
        this.alarms = alarms;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.alarm_item, parent, false);
        }

        String alarmTime = getItem(position);
        TextView alarmTextView = convertView.findViewById(R.id.alarm_time);
        Switch alarmSwitch = convertView.findViewById(R.id.alarm_switch);

        alarmTextView.setText(alarmTime);
        alarmSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
        });

        return convertView;
    }
}
