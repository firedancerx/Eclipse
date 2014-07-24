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

  public void onButtonClick(View view) {
    Intent intent = new Intent();
    intent.setAction("com.allmycode.ACTION");
    sendBroadcast(intent);
  }
}
