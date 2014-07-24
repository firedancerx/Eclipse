package com.allmycode.results;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;

public class GiveResultActivity extends Activity 
          implements OnKeyListener, OnClickListener {

  Button button;
  EditText editText;
  StringBuffer buffer = new StringBuffer();

  @Override
  public void onCreate(Bundle state) {
    super.onCreate(state);

    setContentView(R.layout.giver);
    editText = (EditText) findViewById(R.id.editText1);
    editText.setOnKeyListener(this);
    
    button = (Button) findViewById(R.id.button2);
    button.setOnClickListener(this);
  }

  @Override
  public boolean onKey(View v, int keyCode, 
                               KeyEvent event) {
    buffer.append(keyCode);
    return false;
  }

  @Override
  public void onClick(View arg0) {
    Intent intent = new Intent();
    intent.putExtra
      ("text", editText.getText().toString());
    setResult(RESULT_OK, intent);
    finish();
  }
}
