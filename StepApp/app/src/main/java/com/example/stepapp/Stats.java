package com.example.stepapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Stats extends AppCompatActivity {
    //TODO call the database and get information
    //TODO make the layout scrollable and keeps text on seperate lines

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_layout);

        TextView stepCountView = findViewById(R.id.total_steps_count_view);
        TextView milesCountView = findViewById(R.id.total_miles_count_view);

        databaseHelper db = new databaseHelper(getApplicationContext());
        int steps = db.getTotalSteps();

        stepCountView.setText(String.valueOf(steps));
        milesCountView.setText(String.valueOf(steps/2000));
        // optional if intent stored information
        //Intent intent = getIntent();
        //Parcelable value = intent.getStringExtra("database");
        //(databaseHelper) value.
    }
}
