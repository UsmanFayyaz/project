package com.example.abdulbasit.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class alarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        Bundle extra = intent.getBundleExtra("data");
        ArrayList<structure> arr = extra.getParcelableArrayList("arr");
        int pos = intent.getIntExtra("requestCode", 0);

        Toast.makeText(context, "Wake up", Toast.LENGTH_LONG).show();

        Intent i = new Intent();

        i.setClassName(context.getPackageName(), alarmPreview.class.getName());
        i.putExtra("data", arr);
        i.putExtra("requestCode", pos);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
