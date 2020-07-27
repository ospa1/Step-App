package com.example.stepapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    Button login;
    Button register;
    TextView userName;
    TextView password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*TODO check if already created an account and load the main_activity class
           or have to login every time */
        setContentView(R.layout.login_screen);

        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButton);
        userName  = findViewById(R.id.usernameText);
        //Todo see how to correctly handle a password
        password = findViewById(R.id.passwordText);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo see if its better for db to be in a higher scope
                databaseHelper db = new databaseHelper(com.example.stepapp.login.this);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo see if its better for db to be in a higher scope
                databaseHelper db = new databaseHelper(com.example.stepapp.login.this);
            }
        });
    }
}
