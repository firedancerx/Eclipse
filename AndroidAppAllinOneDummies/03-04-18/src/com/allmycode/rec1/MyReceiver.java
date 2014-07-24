package com.allmycode.rec1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
  private int number;
  private boolean INTENTIONALLY_FAIL = false;

  public MyReceiver(int number) {
    this.number = number;
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    String letter =
        intent.getData().getSchemeSpecificPart();
    Log.i("MyRecevier", number + " Received a broadcast "
        + letter);
    
    if (INTENTIONALLY_FAIL) {
      setResultCode(Activity.RESULT_CANCELED);
      return;
    }

    if (getResultCode() == Activity.RESULT_OK) {
      Bundle bundle = getResultExtras(true);
      ArrayList<Integer> receiverNums =
          bundle.getIntegerArrayList("receiverNums");
      if (receiverNums != null) {
        receiverNums.add(new Integer(number));
      }
      setResultExtras(bundle);
    }
  }
}
