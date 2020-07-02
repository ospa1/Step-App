package com.example.stepapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private float numSteps = 0;
    private boolean running = false;
    private TextView Steps;
    private TextView numMiles;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.banana);

        mPrefs = getSharedPreferences("user", 0);
        numSteps = mPrefs.getFloat("steps",0);

        Steps = findViewById(R.id.counterView);
        numMiles = findViewById(R.id.numMilesView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        View StartButton = findViewById(R.id.startButton);
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
            }
        });

        View StopButton = findViewById(R.id.stopButton);
        StopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = true;
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (sensor != null){
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
        else{
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;

        //unregister listener to stop counting steps when away;
        sensorManager.unregisterListener(this);

        //export steps
        //onStop and onDestroy can be killed by the system


        //stores the total number of steps
        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putFloat("steps", numSteps);
        ed.commit();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running){
            numSteps = event.values[0];
            Steps.setText(String.valueOf(numSteps));
            numMiles.setText(String.valueOf(numSteps/2000));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
