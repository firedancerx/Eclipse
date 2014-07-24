package com.allmycode.rec1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
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
    filter.addDataScheme("letter");

    IntentFilter filter1 = new IntentFilter(filter);
    IntentFilter filter2 = new IntentFilter(filter);
    IntentFilter filter3 = new IntentFilter(filter);

    MyReceiver receiver1 = new MyReceiver(1);
    MyReceiver receiver2 = new MyReceiver(2);
    MyReceiver receiver3 = new MyReceiver(3);

    registerReceiver(receiver1, filter1);
    registerReceiver(receiver2, filter2);
    registerReceiver(receiver3, filter3);

    Intent intent = new Intent();
    intent.setAction("com.allmycode.ACTION");
    intent.setData(Uri.parse("letter:A"));

    MyEndResultReceiver resultReceiver =
        new MyEndResultReceiver();
    ArrayList<Integer> receiverNums =
        new ArrayList<Integer>();
    Bundle bundle = new Bundle();
    bundle.putIntegerArrayList("receiverNums",
        receiverNums);

    sendStickyOrderedBroadcast(intent, resultReceiver,
        null, Activity.RESULT_OK, null, bundle);
  }
}

class MyEndResultReceiver extends BroadcastReceiver {

  final static String CLASSNAME = "MyEndResultReceiver";

  @Override
  public void onReceive(Context context, Intent intent) {
    if (getResultCode() == Activity.RESULT_OK) {
      Bundle bundle = getResultExtras(true);
      ArrayList<Integer> receiverNums =
          bundle.getIntegerArrayList("receiverNums");
      Log.i(CLASSNAME, receiverNums.toString());
    } else {
      Log.i(
          CLASSNAME,
          "Result code: "
              + Integer.toString(getResultCode()));
    }
  }
}
