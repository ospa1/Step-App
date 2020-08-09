package com.example.stepapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Stats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_layout);

        TextView stepCountView = findViewById(R.id.total_steps_count_view);
        TextView milesCountView = findViewById(R.id.total_miles_count_view);

        // get the database and the total number of steps from it
        databaseHelper db = new databaseHelper(getApplicationContext());
        int steps = db.getTotalSteps();

        // update the information
        stepCountView.setText(String.valueOf(steps));
        milesCountView.setText(String.valueOf(steps/2000));
    }
}
