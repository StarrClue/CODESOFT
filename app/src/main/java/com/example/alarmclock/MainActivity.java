package com.example.alarmclock;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button setAlarmButton;
    private ListView alarmListView;
    private ArrayList<String> alarmList = new ArrayList<>();
    private AlarmListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setAlarmButton = findViewById(R.id.set_alarm_button);
        alarmListView = findViewById(R.id.alarm_list);
        adapter = new AlarmListAdapter(this, alarmList);
        alarmListView.setAdapter(adapter);

        setAlarmButton.setOnClickListener(v -> showTimePickerDialog());
    }


    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                MainActivity.this,
                (view, hourOfDay, minute1) -> {
                    setAlarm(hourOfDay, minute1);
                    showRingtonePicker();  // Open ringtone picker after setting alarm time
                },
                hour,
                minute,
                false
        );
        timePickerDialog.show();
    }


    private void setAlarm(int hourOfDay, int minute) {
        String alarmTime = String.format("%02d:%02d", hourOfDay, minute);
        alarmList.add(alarmTime);
        adapter.notifyDataSetChanged();
    }


    public void showRingtonePicker() {
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri ringtoneUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            if (ringtoneUri != null) {

                Toast.makeText(this, "Alarm tone selected", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
