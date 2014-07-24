package com.allmycode.rec1;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MyActivity extends Activity {
  MyReceiver receiver = new MyReceiver();

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }

  @Override
  public void onResume() {
    super.onResume();
    IntentFilter filter = new IntentFilter();
    filter.addAction("com.allmycode.ACTION");
    registerReceiver(receiver, filter);
  }

  @Override
  public void onPause() {
    super.onPause();
    unregisterReceiver(receiver);
  }

  public void onButtonClick(View view) {
    Intent intent = new Intent();
    intent.setAction("com.allmycode.ACTION");
    sendBroadcast(intent);
  }
}
