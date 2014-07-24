package com.allmycode.rec1;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
  private int number;
  
  boolean isFirstCall = true;
  Calendar now;
  long time;

  public MyReceiver(int number) {
    this.number = number;
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    String letter =
        intent.getData().getSchemeSpecificPart();
	if (isFirstCall) {
	  now = Calendar.getInstance();
	  time = now.getTimeInMillis(); 
	  isFirstCall = false;  
	}
	Log.i("MyRecevier", number + " Received a broadcast "
        + letter + " " + time);
  }
}
