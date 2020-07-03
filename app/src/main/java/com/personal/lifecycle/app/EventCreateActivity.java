package com.personal.lifecycle.app;

import static com.personal.lifecycle.constants.AppConstants.*;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
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

public class EventCreateActivity extends AppCompatActivity implements DialogInterface.OnShowListener {
    private String mStartTime;
    private String mEndTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_create_activity_main);

        Toolbar toolbar = findViewById(R.id.app_toolbar);
        toolbar.setTitle(R.string.create_event_toolbar_title);
        setSupportActionBar(toolbar);

        setListeners();
        initLayout();
    }

    private void initLayout() {
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_START_EVENT_HOUR) && intent.hasExtra(EXTRA_END_EVENT_HOUR)) {
        } else {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            TextView tv = findViewById(R.id.event_start_textview);
            tv.setText(hour + ":" + String.format("%02d", min));

            min += 5;
            if (min >= 60) {
                hour = (hour + 1) % 24;
                min -= 60;
            }
            tv = findViewById(R.id.event_end_textview);
            tv.setText(hour + ":" + String.format("%02d", min));
        }
    }

    private void setListeners() {
        findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.event_name_edittext);
                String title = editText.getText().toString();

                Intent intent = new Intent();
                intent.putExtra(KEY_EVENT_TITLE, title);
                intent.putExtra(KEY_START_TIME, mStartTime);
                intent.putExtra(KEY_END_TIME, mEndTime);
                setResult(RESULT_OK, intent);
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
                showTimePickerDialog(true);
            }
        });
        findViewById(R.id.event_end_imageview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(false);
            }
        });
    }

    private void showTimePickerDialog(boolean start) {
        int hour;
        int min;
        if (start) {
            if (getIntent().hasExtra(EXTRA_START_EVENT_HOUR) || getIntent().hasExtra(EXTRA_START_EVENT_MIN)) {
                hour = getIntent().getIntExtra(EXTRA_START_EVENT_HOUR, 0);
                min = getIntent().getIntExtra(EXTRA_START_EVENT_MIN, 0);
            } else {
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                min = calendar.get(Calendar.MINUTE);
            }
            TimePickerDialog dialog = new TimePickerDialog(this,
                    mStartTimerListener, hour, min, DateFormat.is24HourFormat(this));
            dialog.setOnShowListener(this);
            dialog.show();
        } else {
            if (getIntent().hasExtra(EXTRA_START_EVENT_HOUR) || getIntent().hasExtra(EXTRA_START_EVENT_MIN)) {
                hour = getIntent().getIntExtra(EXTRA_START_EVENT_HOUR, 0);
                min = getIntent().getIntExtra(EXTRA_START_EVENT_MIN, 0);
            } else {
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                min = calendar.get(Calendar.MINUTE);

                min += 5;
                if (min >= 60) {
                    min -= 60;
                    hour = (hour + 1) % 24;
                }
            }
            TimePickerDialog dialog = new TimePickerDialog(this,
                    mEndTimerListener, hour, min, DateFormat.is24HourFormat(this));
            dialog.setOnShowListener(this);
            dialog.show();
        }
        findViewById(R.id.event_start_imageview).setEnabled(false);
        findViewById(R.id.event_end_imageview).setEnabled(false);
    }

    private TimePickerDialog.OnTimeSetListener mStartTimerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            AppLog.d(TAG, "onStartTimeSet, hour = " + hourOfDay + ", minute = " + minute);
            TextView tv = findViewById(R.id.event_start_textview);
            mStartTime = hourOfDay + ":" + String.format("%02d", minute);
            tv.setText(mStartTime);
        }
    };

    private TimePickerDialog.OnTimeSetListener mEndTimerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            AppLog.d(TAG, "onEndTimeSet, hour = " + hourOfDay + ", minute = " + minute);
            TextView tv = findViewById(R.id.event_end_textview);
            mEndTime = hourOfDay + ":" + String.format("%02d", minute);
            tv.setText(mEndTime);
        }
    };

    @Override
    public void onShow(DialogInterface dialogInterface) {
        AppLog.d(TAG, "onDialogShow");
        findViewById(R.id.event_start_imageview).setEnabled(true);
        findViewById(R.id.event_end_imageview).setEnabled(true);
    }
}
