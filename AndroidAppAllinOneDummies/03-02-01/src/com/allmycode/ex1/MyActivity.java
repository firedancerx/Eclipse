package com.allmycode.ex1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MyActivity extends Activity {
  final String THE_ACTION = "com.allmycode.action.MY_ACTION";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Intent intent = new Intent();
    intent.setAction(THE_ACTION);
    startActivity(intent);

  }
}