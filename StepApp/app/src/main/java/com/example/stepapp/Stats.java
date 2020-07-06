package com.example.stepapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Stats extends AppCompatActivity {
    //TODO call the database and get information

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_layout);

        // optional if intent stored information
        //Intent intent = getIntent();
        //String value = intent.getStringExtra("key"); //if it's a string you stored.
    }
}
