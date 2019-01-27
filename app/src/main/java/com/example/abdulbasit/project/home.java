package com.example.abdulbasit.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    sqlite myDataBase;
    ArrayList<structure> arr = new ArrayList<structure>();
    ArrayAdapter myAdapter;
    ListView myListView;
    private static final int addEdit_activity_request_code = 0;
    private static final int specific_activity_request_code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myDataBase = new sqlite(this);

        populateArrayAdapter();
    }

    public void add(View view) {
        Intent i = new Intent(this, addEdit.class);
        i.putExtra("data", arr);
        i.putExtra("position", arr.size());
        i.putExtra("specific", false);
        startActivityForResult(i, addEdit_activity_request_code);
    }

    public void viewSlot(int pos) {
        Intent i = new Intent(this, SpecifcSlot.class);

        i.putExtra("data", arr);
        i.putExtra("position", pos);
        i.putExtra("specific", false);
        startActivityForResult(i, specific_activity_request_code);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == addEdit_activity_request_code) {
            if (resultCode == RESULT_OK) {

                arr = data.getParcelableArrayListExtra("data");

                myAdapter = new custom_list(this, arr);
                myListView = (ListView) findViewById(R.id.myList);
                myListView.setAdapter(myAdapter);


                structure sts = arr.get(arr.size() - 1);
                myDataBase.insertData(sts.title, sts.date, sts.time,arr.size()-1,"true");
                myListView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                viewSlot(position);
                            }
                        }
                );
            }
        }

        if (requestCode == specific_activity_request_code) {
            if (resultCode == RESULT_OK) {

                arr = data.getParcelableArrayListExtra("data");
                int pos = data.getIntExtra("position", 0);

                myAdapter = new custom_list(this, arr);
                myListView = (ListView) findViewById(R.id.myList);
                myListView.setAdapter(myAdapter);

                if(pos == -1)
                    return;

                structure sts = arr.get(pos);
                if(myDataBase.updateData(sts.title, sts.date, sts.time,pos,"true"))
                    Toast.makeText(this,"succefully updated",Toast.LENGTH_LONG).show();

                myListView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                viewSlot(position);
                            }
                        }
                );
            }
        }
    }

    public void populateArrayAdapter() {
        Cursor res = myDataBase.getAllData();
        structure sts;

        if (res.getCount() == 0)
            return;

        while (res.moveToNext()) {
            sts = new structure();
            sts.title = res.getString(1);
            sts.date = res.getString(2);
            sts.time = res.getString(3);
            sts.isSwitch=res.getString(5);

            arr.add(sts);
        }

        myAdapter = new custom_list(this, arr);
        myListView = (ListView) findViewById(R.id.myList);
        myListView.setAdapter(myAdapter);

        myListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        viewSlot(position);
                    }
                }
        );
    }
}
