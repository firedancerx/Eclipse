package com.allmycode.rec1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context arg0, Intent arg1) {
    Log.i("MyRecevier", "Received a broadcast");
  }
}
