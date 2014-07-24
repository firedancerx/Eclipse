package com.allmycode.demo1;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

public class Demo1App extends Application {
    private static final String CLASSNAME = "Demo1App";

    @Override
    public void onCreate() {
        Log.v(CLASSNAME, "onCreate");
    }
    
    @Override
    public void onConfigurationChanged
                               (Configuration config) {
        Log.v(CLASSNAME, "onConfigurationChanged");
    }
    
    @Override
    public void onLowMemory() {
        Log.v(CLASSNAME, "onLowMemory");
    }
    
    @Override
    public void onTerminate() {
        Log.v(CLASSNAME, "onTerminate");
    }
}
