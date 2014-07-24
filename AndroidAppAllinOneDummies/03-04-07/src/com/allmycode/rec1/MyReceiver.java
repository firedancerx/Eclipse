package com.allmycode.rec1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
  private int number;

  public MyReceiver(int number) {
    this.number = number;
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    String letter =
        intent.getData().getSchemeSpecificPart();
    Log.i("MyRecevier", number + " Received a broadcast "
        + letter);
  }
}
