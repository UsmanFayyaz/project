package com.example.abdulbasit.project;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    structure st;
    ArrayList<structure> arr = new ArrayList<structure>();
    ArrayAdapter myAdapter;
    ListView myListView;
    private static final int second_activity_request_code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void add(View view) {
        Intent i = new Intent(this, addEdit.class);
        startActivityForResult(i, second_activity_request_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == second_activity_request_code) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                String returninp = data.getStringExtra("input");
                String returndate = data.getStringExtra("date");
                String returntime = data.getStringExtra("time");

                structure sts;
                sts=new structure(returninp,returndate,returntime);

                arr.add(sts);

                myAdapter = new custom_list(this, arr);
                myListView = (ListView) findViewById(R.id.myList);
                myListView.setAdapter(myAdapter);
            }
        }
    }
}