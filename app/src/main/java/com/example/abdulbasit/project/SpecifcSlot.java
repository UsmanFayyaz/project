package com.example.abdulbasit.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SpecifcSlot extends AppCompatActivity {

    ArrayList<structure> arr;
    int position;
    structure sts;

    TextView title;
    TextView date;
    TextView time;
    private static final int second_activity_request_code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifc_slot);

        title = (TextView) findViewById(R.id.Title);
        date = (TextView) findViewById(R.id.dateText);
        time = (TextView) findViewById(R.id.timeText);

        Intent i = getIntent();
        arr = i.getParcelableArrayListExtra("data");
        position = i.getIntExtra("position", 1);

        sts = arr.get(position);

        title.setText(sts.title);
        date.setText(sts.date);
        time.setText(sts.time);
    }

    public void edit(View view) {
        Intent i = new Intent(this, addEdit.class);
        i.putExtra("data", arr);
        i.putExtra("position", position);
        i.putExtra("specific", true);
        startActivityForResult(i, second_activity_request_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == second_activity_request_code) {
            if (resultCode == RESULT_OK) {
                arr = data.getParcelableArrayListExtra("data");

                Intent i = new Intent();
                i.putExtra("data", arr);
                i.putExtra("position", position);
                setResult(RESULT_OK, i);
                finish();
            }
        }
    }
}
