package com.allmycode.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyWeatherService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, R.string.service_bound,
                       Toast.LENGTH_SHORT).show();
        System.out.println("onBindCalled");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, 
                              int flags, int startId) {
        Toast.makeText(this, R.string.service_started, 
                       Toast.LENGTH_SHORT).show();
        System.out.println("onStartCommandCalled");

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, R.string.service_destroyed, 
                       Toast.LENGTH_SHORT).show();
    }
}
