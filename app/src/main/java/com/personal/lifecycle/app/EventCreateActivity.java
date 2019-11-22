package com.personal.lifecycle.app;

import static com.personal.lifecycle.constants.AppConstants.*;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.personal.lifecycle.R;
import com.personal.lifecycle.util.AppLog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventCreateActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_create_activity_main);

        Toolbar toolbar = findViewById(R.id.app_toolbar);
        toolbar.setTitle(R.string.create_event_toolbar_title);
        setSupportActionBar(toolbar);

        findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
        findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        findViewById(R.id.event_start_imageview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });
        findViewById(R.id.event_end_imageview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private void initTimes() {
        Calendar calendar = Calendar.getInstance();
        TextView textView = findViewById(R.id.event_start_textview);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(this,
                this, hour, min, DateFormat.is24HourFormat(this));
        dialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        AppLog.d(TAG, "onTimeSet, hour = " + hourOfDay + ", minute = " + minute);
    }
}
