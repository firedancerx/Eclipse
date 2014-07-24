package com.allmycode.sensor;

import static android.hardware.Sensor.TYPE_ACCELEROMETER;
import static android.hardware.Sensor.TYPE_MAGNETIC_FIELD;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity {
  SensorManager sensorManager;
  Sensor magFieldSensor, accelerometer;
  SensorEventListener sensorListener;
  LocationListener locationListener; 
  LocationManager locationManager;
  TextView orientationView, locationView;

  private float[] gravityValues = new float[3];
  private float[] geoMagnetValues = new float[3];
  private float[] orientation = new float[3];
  private float[] rotationMatrix = new float[9];

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.main);
    sensorManager = (SensorManager) 
        getSystemService(Context.SENSOR_SERVICE);
    magFieldSensor = sensorManager
            .getDefaultSensor(TYPE_MAGNETIC_FIELD);
    accelerometer = sensorManager
            .getDefaultSensor(TYPE_ACCELEROMETER);  
    
    sensorListener = new MySensorEventListener();
    
    locationListener = new MyLocationListener();
    locationManager = (LocationManager) 
        getSystemService(Context.LOCATION_SERVICE);  
    
    orientationView = 
        (TextView) findViewById(R.id.orientationView);
    locationView = 
        (TextView) findViewById(R.id.locationView);
  }

  @Override
  protected void onResume() {
    super.onResume();
    sensorManager.registerListener(sensorListener, 
        magFieldSensor, SensorManager.SENSOR_DELAY_UI);
    sensorManager.registerListener(sensorListener, 
        accelerometer, SensorManager.SENSOR_DELAY_UI);
    
    locationManager.requestLocationUpdates
    (LocationManager.GPS_PROVIDER,
        0, 0, locationListener);
  }

  @Override
  protected void onPause() {
    super.onPause();
    sensorManager.unregisterListener(sensorListener);
    locationManager.removeUpdates(locationListener);
  }
  
  class MySensorEventListener implements SensorEventListener {

    @Override
    public void onSensorChanged(SensorEvent event) {

      int sensorEventType = event.sensor.getType();
      
      if (sensorEventType == Sensor.TYPE_ACCELEROMETER) {
        System.arraycopy
          (event.values, 0, gravityValues, 0, 3);
        
      } else if (sensorEventType == 
                            Sensor.TYPE_MAGNETIC_FIELD) {
        System.arraycopy
          (event.values, 0, geoMagnetValues, 0, 3);
        
      } else {
        return;     
      }

      if (SensorManager.getRotationMatrix(rotationMatrix,
          null, gravityValues, geoMagnetValues)) {

        SensorManager.getOrientation(rotationMatrix,
            orientation);

        orientationView.setText
             ("Yaw:   " + orientation[0] + "\n"
            + "Pitch: " + orientation[1] + "\n" 
            + "Roll:  " + orientation[2]);
      }
    }   
    
    @Override
    public void onAccuracyChanged(Sensor sensor,
        int accuracy) {
      if (accuracy <= 1) {
        Toast.makeText(MyActivity.this, "Please shake the " + 
          "device in a figure eight pattern to " +
          "improve sensor accuracy!", Toast.LENGTH_LONG)
          .show();
      }
    }
  }
  
  class MyLocationListener implements LocationListener {

    @Override
    public void onLocationChanged(Location location) {
      locationView.setText
          ("Latitude:  " + location.getLatitude() + "\n"
         + "Longitude: " + location.getLongitude());    
    }

    @Override
    public void onProviderDisabled(String provider) {      
    }

    @Override
    public void onProviderEnabled(String provider) {      
    }

    @Override
    public void onStatusChanged(String provider,
        int status, Bundle extras) {      
    }    
  }
}
