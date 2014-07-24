package com.allmycode.demos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ServiceConsumerActivity extends Activity {
    Intent intent = new Intent();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        intent.setAction("com.allmycode.WEATHER");
    }

    public void onStartClick(View view) {
    	System.out.println("onStart called");
        startService(intent);
    }

    public void onStopClick(View view) {
        stopService(intent);
    }
}
