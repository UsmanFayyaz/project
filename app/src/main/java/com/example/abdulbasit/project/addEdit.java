package com.example.abdulbasit.project;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class addEdit extends AppCompatActivity {

    Button dateButton;
    Button timeButton;
    TextView mDisplayDate;
    TextView mDisplayTime;
    structure sts;
    ArrayList<structure> arr;
    int position;
    boolean edit_add;
    EditText input;
    String date;
    String time;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    TimePickerDialog.OnTimeSetListener mTimeSetListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        Intent i = getIntent();
        arr = i.getParcelableArrayListExtra("data");
        position = i.getIntExtra("position", 1);
        edit_add = i.getBooleanExtra("specific", false);
        dateButton = (Button) findViewById(R.id.dateButton);
        timeButton = (Button) findViewById(R.id.timeButton);
        mDisplayDate = (TextView) findViewById(R.id.dateText);
        mDisplayTime = (TextView) findViewById(R.id.timeText);
        input = (EditText) findViewById(R.id.input);

        if (!edit_add) {
            Calendar d = Calendar.getInstance();
            int year = d.get(Calendar.YEAR);
            int month = d.get(Calendar.MONTH);
            int day = d.get(Calendar.DAY_OF_MONTH);

            int hour = d.get(Calendar.HOUR_OF_DAY);
            int minute = d.get(Calendar.MINUTE);
            month++;
            date = day + "/" + month + "/" + year;
            mDisplayDate.setText(date);
            time = hour + ":" + minute + ":00";
            mDisplayTime.setText(time);
        } else {
            sts = arr.get(position);
            input.setText(sts.description);
            date = sts.date;
            time = sts.time;
            mDisplayDate.setText(date);
            mDisplayTime.setText(time);
        }


        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        addEdit.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;

                date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(
                        addEdit.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListner,
                        hour, minute, false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mTimeSetListner = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                time = hour + ":" + minute + ":00";
                mDisplayTime.setText(time);
            }
        };
    }

    public void save(View view) {


        String dateInString = date + " " + time;
        Date inputDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm");

        try {
            inputDate = sdf.parse(dateInString);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long userInputTime = inputDate.getTime();

        Intent intent = new Intent(addEdit.this, alarm.class);
        PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.set(AlarmManager.RTC_WAKEUP, userInputTime, pending);
///////////////////////////////////////////////////////////////////////////////
        Log.d("usman", userInputTime + "");
        Calendar calendar = Calendar.getInstance();

        Log.d("usman", calendar.getTimeInMillis() + "");
///////////////////////////////////////////////////////////////////////////////
        sts = new structure(input.getText().toString(), mDisplayDate.getText().toString(), mDisplayTime.getText().toString());
        if (!edit_add) {
            arr.add(sts);
        } else {
            arr.remove(position);
            arr.add(position, sts);
        }
        Intent i = new Intent();
        i.putExtra("data", arr);
        setResult(RESULT_OK, i);
        finish();
    }
}
