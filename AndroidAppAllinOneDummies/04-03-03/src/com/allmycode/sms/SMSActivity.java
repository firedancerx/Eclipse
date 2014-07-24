package com.allmycode.sms;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SMSActivity extends Activity implements
    OnClickListener {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    ((Button) findViewById(R.id.button1))
        .setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    SmsManager smsMgm = SmsManager.getDefault();

    smsMgm.sendTextMessage("2345556789", null,
        "Hello world", null, null);
  }
}
