package com.allmycode.demo1A;

import android.os.Bundle;
import android.util.Log;

public class OtherAppActivity extends MyActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    void logStuff(String message) {
        Log.v("OtherAppActivity", message);
    }
}