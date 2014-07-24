package com.allmycode.rec1;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
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
	  MyReceiver receiver1 = new MyReceiver(1);
	  MyReceiver receiver2 = new MyReceiver(2);
	  MyReceiver receiver3 = new MyReceiver(3);

	  Intent intent = new Intent();
	  intent.setAction("com.allmycode.ACTION");
	  intent.setData(Uri.parse("letter:A"));

	  registerReceiver(receiver1, filter);
	  
	  sendStickyBroadcast(intent);
	        
	  registerReceiver(receiver2, filter);
	  
	  removeStickyBroadcast(intent);
	  
	  registerReceiver(receiver3, filter);
	}

}
