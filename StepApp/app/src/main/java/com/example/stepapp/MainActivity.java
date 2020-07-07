package com.example.stepapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    databaseHelper db = new databaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(R.drawable.banana);

        Steps = findViewById(R.id.counterView);
        numMiles = findViewById(R.id.numMilesView);

        //get stored steps  ---- probably not needed
        mPrefs = getSharedPreferences("user", 0);
        numSteps = mPrefs.getFloat("steps",0);

        //get the step sensor
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

        //TODO export steps
        //onStop and onDestroy can be killed by the system
        db.addSteps((int) numSteps);


        //stores the total number of steps
        SharedPreferences.Editor ed = mPrefs.edit();
        ed.putFloat("steps", numSteps);
        ed.commit();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        // Update values when the sensor updates
        if (running){
            numSteps = event.values[0];
            Steps.setText(String.valueOf(numSteps));
            numMiles.setText(String.valueOf(numSteps/2000));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Set up menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.stats:
                //go to stats page
                Intent intent = new Intent(MainActivity.this, Stats.class);
                //intent.putExtra("database", (Parcelable) db);
                MainActivity.this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
