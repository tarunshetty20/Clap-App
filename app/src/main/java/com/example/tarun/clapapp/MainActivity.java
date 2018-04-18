package com.example.tarun.clapapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager msensor;
    private Sensor mproxsensor;
    private TextView mdisplay;
    MediaPlayer msound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msensor = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mproxsensor = msensor.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        msound = MediaPlayer.create(this, R.raw.note6_a);
        mdisplay = (TextView) findViewById(R.id.textclapp);

    }

    @Override
    protected void onResume() {

        super.onResume();

        msensor.registerListener(this, mproxsensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {

        super.onPause();
        msensor.unregisterListener(this);
    }


    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float distance = event.values[0];

        if (distance < 3.0) {


            msound.start();
            mdisplay.setText("Clapped");
            return;
        }

        mdisplay.setText("Not Clapped");
    }
}