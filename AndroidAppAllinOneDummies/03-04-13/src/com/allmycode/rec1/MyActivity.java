package com.allmycode.rec1;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MyActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  public void onButtonClick(View view) {
    IntentFilter filter = new IntentFilter();
    filter.addAction("com.allmycode.ACTION");
    filter.addAction("com.allmycode.OTHER_ACTION");
    filter.addDataScheme("letter");
    MyReceiver receiver1 = new MyReceiver(1);
    MyReceiver receiver2 = new MyReceiver(2);

    Intent returnedIntent = registerReceiver(receiver1, filter);

    Log.i("MyActivity", getStatus(returnedIntent));

    Intent intentAct = new Intent();
    intentAct.setAction("com.allmycode.ACTION");
    intentAct.setData(Uri.parse("letter:A"));
    sendStickyBroadcast(intentAct);

    Intent intentOth = new Intent();
    intentOth.setAction("com.allmycode.OTHER_ACTION");
    intentOth.setData(Uri.parse("letter:O"));
    sendStickyBroadcast(intentOth);

    returnedIntent = registerReceiver(receiver2, filter);
    Log.i("MyActivity", getStatus(returnedIntent));

    /*
     *  A kludge added to keep the code from
     *  generating lingering broadcasts and
     *  receivers.
     */
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    removeStickyBroadcast(intentAct);
    removeStickyBroadcast(intentOth);
    unregisterReceiver(receiver1);
    unregisterReceiver(receiver2);
  }

  private String getStatus(Intent returnedIntent) {
    if (returnedIntent == null) {
      return "null";
    } else {
      return returnedIntent.toString();
    }
  }
}
