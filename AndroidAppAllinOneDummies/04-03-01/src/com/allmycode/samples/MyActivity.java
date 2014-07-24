package com.allmycode.samples;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyActivity extends Activity implements
    OnClickListener {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    ((Button) findViewById(R.id.dialButton))
        .setOnClickListener(this);
    ((Button) findViewById(R.id.callButton))
        .setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    boolean isOk = true;
    Intent intent = new Intent();

    if (!deviceIsAPhone()) {
      displayAlert();
      isOk = false;
    }

    if (isOk) {
      switch (view.getId()) {
      case R.id.dialButton:
        intent.setAction(Intent.ACTION_DIAL);
        break;
      case R.id.callButton:
        intent.setAction(Intent.ACTION_CALL);
        break;
      default:
        isOk = false;
      }
      intent.setData(Uri.parse("tel:234-555-6789"));
    }

    if (isOk) {
      startActivity(intent);
    }
  }

  boolean deviceIsAPhone() {
    TelephonyManager manager = (TelephonyManager) 
        getSystemService(Context.TELEPHONY_SERVICE);
    return manager.getPhoneType() != 
                   TelephonyManager.PHONE_TYPE_NONE;
  }

  void displayAlert() {
    AlertDialog.Builder alertBuilder =
        new AlertDialog.Builder(this);
    alertBuilder
      .setTitle("Not a telephone!")
      .setMessage("This device can't phone make calls!")
      .setPositiveButton("OK", new MyDialogListener())
      .show();
  }

  class MyDialogListener implements
      DialogInterface.OnClickListener {
    public void onClick(DialogInterface dialog,
        int whichButton) {
    }
  }  
}
