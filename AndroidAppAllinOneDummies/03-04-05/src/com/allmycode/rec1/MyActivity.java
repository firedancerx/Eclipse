package com.allmycode.rec1;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
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
	  MyReceiver receiver = new MyReceiver();

	  registerReceiver(receiver, filter);
	  registerReceiver(receiver, filter);
	  registerReceiver(new MyReceiver(), filter);

	  Intent intent = new Intent();
	  intent.setAction("com.allmycode.ACTION");
	  sendBroadcast(intent);

	  Log.i("MyActivity",
	      "Sent a broadcast; about to send another...");

	  sendBroadcast(intent);
	}

}
