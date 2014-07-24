package com.allmycode.demo1;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

public abstract class MyActivity extends Activity {
    
    abstract void logStuff(String message);
    
    public void addBreak(View view) {
        logStuff("------");
    }
       
    /** Called when the activity is created (either 
     *  for the first time or after having been
     *  Destroyed. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logStuff("onCreate");
    }
 
    /** Called when the activity transitions to 
     *  Paused (on its way to Active) after having
     *  been Stopped.
     */
    @Override
    public void onRestart() {
        super.onRestart();
        logStuff("onRestart");
    }

    /** Called when the activity transitions to
     *  Paused (on its way to Active), either for 
     *  the first time or after the activity has
     *  been Stopped.
     */
    @Override
    public void onStart() {
        super.onStart();
        logStuff("onStart");
    }

    /** Called when the activity transitions
     *  from Paused to Active.
     */
    @Override
    public void onResume() {
        super.onResume();
        logStuff("onResume");
    }

    /** Never called unless you set
     * android:configChanges in the
     * AndroidManifest.xml file.
     */
    @Override
    public void onConfigurationChanged
                               (Configuration config) {
        super.onConfigurationChanged(config);
        logStuff("onConfigurationChanged");
    }
    
    //THIS IS NEW
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        logStuff("onLowMemory");
    }
    
    /** Usually (but not always) called during the
     *  transition from Active to Paused, or during
     *  the transition from Paused to Stopped.
     */
    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        logStuff("onSaveInstanceState");
    }

    /** Called when the activity transitions from
     *  Active to Paused.
     */
    @Override
    public void onPause() {
        super.onPause();
        logStuff("onPause");
    }

    /** Called when the activity transitions from
     *  Paused to Stopped.
     */
    @Override
    public void onStop() {
        super.onStop();
        logStuff("onStop");
    }

    /** Called when the activity transitions from
     *  Stopped to Destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        logStuff("onDestroy");
    }
}
