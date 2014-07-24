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
    filter.addDataScheme("letter");

    IntentFilter filter1 = new IntentFilter(filter);
    IntentFilter filter2 = new IntentFilter(filter);
    IntentFilter filter3 = new IntentFilter(filter);

    filter1.setPriority(17);
    filter2
        .setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY - 1);
    filter3.setPriority(-853);

    MyReceiver receiver1 = new MyReceiver(1);
    MyReceiver receiver2 = new MyReceiver(2);
    MyReceiver receiver3 = new MyReceiver(3);

    registerReceiver(receiver1, filter1);
    registerReceiver(receiver2, filter2);
    registerReceiver(receiver3, filter3);

    Intent intent = new Intent();
    intent.setAction("com.allmycode.ACTION");
    intent.setData(Uri.parse("letter:A"));
    
    sendOrderedBroadcast(intent, null);
    Log.i("MyActivity",
        "Now watch the log entries pour in...");  
  }
}
