package com.example.abdulbasit.project;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class alarmPreview extends Activity {
    ArrayList<structure> arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_preview);

        final Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        Toast.makeText(this, "Wake up", Toast.LENGTH_LONG).show();

        Intent i = getIntent();
        arr = i.getParcelableArrayListExtra("data");
        int pos = i.getIntExtra("requestCode", 0);

        structure sts = arr.get(pos);

        TextView desc = (TextView) findViewById(R.id.desc);

        desc.setText(sts.title);

        startService(new Intent(this, service.class));
    }

    public void stop(View view) {
        stopService(new Intent(this, service.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, service.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
