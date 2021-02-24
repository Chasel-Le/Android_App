package com.example.helper;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LotteryActivity extends AppCompatActivity {
    SensorManager mSensorManager;
    float X_lateral = 0;
    float Y_longitudinal = 0;
    float Z_vertical = 0;
    TextView number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery);
        number = findViewById(R.id.number);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
    }
    final SensorEventListener myAccelerometerListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(Math.abs(X_lateral - event.values[0]) > 10 || Math.abs(Y_longitudinal - event.values[1]) > 10 || Math.abs(Z_vertical - event.values[2]) > 10 ) {
                try {
                    number.setText(""+Math.round((Math.random()*10000)%46+1));
                } catch (Exception e)
                {
                    Log.d("sensor", e.toString());
                }
            }
            X_lateral = event.values[0];
            Y_longitudinal = event.values[1];
            Z_vertical = event.values[2];
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) { }
    };
    public void onPause() {
        mSensorManager.unregisterListener(myAccelerometerListener);
        super.onPause();
    }
    public void onResume() {
        mSensorManager.registerListener(myAccelerometerListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
}