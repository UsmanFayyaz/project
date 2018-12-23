package com.example.abdulbasit.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SpecifcSlot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifc_slot);

        Bundle data = getIntent().getExtras();

        if(data == null){
            return;
        }

        TextView title=(TextView)findViewById(R.id.Title);
        TextView date=(TextView)findViewById(R.id.dateText);
        TextView time=(TextView)findViewById(R.id.timeText);



        title.setText(data.getString("title"));
        date.setText(data.getString("date"));
        time.setText(data.getString("time"));
    }
}
