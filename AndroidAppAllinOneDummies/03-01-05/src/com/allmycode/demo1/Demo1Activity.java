package com.allmycode.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Demo1Activity extends MyActivity {

    /* And don't forget:
     * To show how a process can be taken away, open
     * this app and press the button to open the OtherActivity
     * (which runs by default in a different process) and
     * then open other things (such as browser, etc) and
     * watch the list of processes in the Devices view.
     * You'll be able to see Demo1Activity's process go
     * away and you'll be able to see which lifecycle methods
     * were called.
     */
    
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    void logStuff(String message) {
        Log.i("Demo1Activity", message);
    }
    
    public void startOtherActivity(View view) {
        Intent intent = new Intent();
        intent.setClassName("com.allmycode.demo1", 
            "com.allmycode.demo1.OtherActivity");
        startActivity(intent);
    }
    
    public void startOtherAppActivity(View view) {
        Intent intent = new Intent();
        intent.setClassName("com.allmycode.demo1A", 
            "com.allmycode.demo1A.OtherAppActivity");
        startActivity(intent);
    }
    
    public void startTransparentActivity(View view) {
        Intent intent = new Intent();
        intent.setClassName("com.allmycode.demo1", 
            "com.allmycode.demo1.TranslucentActivity");
        startActivity(intent);
    }
}