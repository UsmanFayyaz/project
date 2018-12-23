package com.example.abdulbasit.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FragmentTransaction ft;
    log_in logFrag;
    sign_up signFrag;
    EditText userName;
    EditText pass;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        logFrag = (log_in) getSupportFragmentManager().findFragmentById(R.id.logInFragment);
        signFrag = (sign_up) getSupportFragmentManager().findFragmentById(R.id.signUpFragment);

        sharedPref = getSharedPreferences("Accounts", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        if (sharedPref.contains("names")) {
            ft.hide(signFrag);
            ft.commit();
        } else {
            ft.hide(logFrag);
            ft.commit();
        }


        View view = findViewById(R.id.signUpFragment);
        userName = (EditText) view.findViewById(R.id.nameInput);
        pass = (EditText) view.findViewById(R.id.passwordInput);
    }

    public void signUp(View view) {
        String message = userName.getText().toString();
        String passw = pass.getText().toString();

        editor.putString("names", message);
        editor.putString("pass", passw);
        editor.apply();

        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
        Intent j = new Intent(this, home.class);
        startActivity(j);
    }

    public void logIn(View view) {
        view = findViewById(R.id.logInFragment);
        userName = (EditText) view.findViewById(R.id.nameInputL);
        pass = (EditText) view.findViewById(R.id.passwordInputL);
        String user = userName.getText().toString();
        String passw = pass.getText().toString();

        if (user.equals(sharedPref.getString("names", "")) && passw.equals(sharedPref.getString("pass", ""))) {
            Toast.makeText(this, "Log-in Succesfull", Toast.LENGTH_LONG).show();

            Intent j = new Intent(this, home.class);
            startActivity(j);
        } else
            Toast.makeText(this, "Details does not exist", Toast.LENGTH_LONG).show();

    }
}

