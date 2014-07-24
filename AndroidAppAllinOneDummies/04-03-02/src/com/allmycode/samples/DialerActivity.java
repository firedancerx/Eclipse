package com.allmycode.samples;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;

public class DialerActivity extends Activity implements
    OnClickListener {

  RadioGroup radioGroup;
  Button button;
  String phoneNumber = "";

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    setContentView(R.layout.dialer_view);
    button = (Button) findViewById(R.id.button1);

    radioGroup =
        (RadioGroup) findViewById(R.id.radioGroup1);
    ((Button) findViewById(R.id.button1))
        .setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    int checkedButton =
        radioGroup.getCheckedRadioButtonId();
    switch (checkedButton) {
    case R.id.radioSpouse:
      phoneNumber = "2225551111";
      break;
    case R.id.radioWork:
      phoneNumber = "2225552222";
      break;
    case R.id.radioMom:
      phoneNumber = "2225553333";
      break;
    }

    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_CALL);
    intent.setData(Uri.parse("tel:" + phoneNumber));
    startActivity(intent);
  }
}



