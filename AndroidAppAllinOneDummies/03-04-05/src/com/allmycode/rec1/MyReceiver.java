package com.allmycode.rec1;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

	boolean isFirstCall = true;
	Calendar now;
	long time;

	@Override
    public void onReceive(Context arg0, Intent arg1) {
	  if (isFirstCall) {
		  now = Calendar.getInstance();
		  time = now.getTimeInMillis(); 
		  isFirstCall = false;  
	  }
      Log.i("MyRecevier", "Received a broadcast " +  time);
    }
}