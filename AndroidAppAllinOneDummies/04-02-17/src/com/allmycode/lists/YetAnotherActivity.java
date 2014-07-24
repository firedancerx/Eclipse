package com.allmycode.lists;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class YetAnotherActivity extends Activity {
  TextView textView;

  @Override
  public void onCreate(Bundle b) {
    super.onCreate(b);
    setContentView(R.layout.yet_another_layout);
    textView = (TextView) findViewById(R.id.textView1);

    Intent intent = getIntent();

    String numberValue =
        intent.getData().getSchemeSpecificPart();

    textView.setText("You selected " + numberValue + ".");
  }
}
