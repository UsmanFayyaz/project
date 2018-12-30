package com.example.abdulbasit.project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

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

            String temp = day + "/" + month + "/" + year;
            mDisplayDate.setText(temp);
            temp = hour + ":" + minute + ":00";
            mDisplayTime.setText(temp);
        } else {
            sts = arr.get(position);
            input.setText(sts.description);
            mDisplayDate.setText(sts.date);
            mDisplayTime.setText(sts.time);
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

                String date = day + "/" + month + "/" + year;
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

                String time = hour + ":" + minute + ":00";
                mDisplayTime.setText(time);
            }
        };
    }

    public void save(View view) {
        sts = new structure(input.getText().toString(), mDisplayDate.getText().toString(), mDisplayTime.getText().toString());
        if (!edit_add) {
            arr.add(sts);
        } else {
            arr.add(position, sts);
        }
        Intent i = new Intent();
        i.putExtra("data", arr);
        setResult(RESULT_OK, i);
        finish();
    }
}
