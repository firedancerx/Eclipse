package com.allmycode.menus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ReminderEditActivity extends Activity {
  EditText editText;

  @Override
  public void onCreate(Bundle b) {
    super.onCreate(b);
    setContentView(R.layout.reminder_edit);
    editText = (EditText) findViewById(R.id.editText1);
  }

  public void onPostButtonClick(View view) {
    Intent intent = new Intent();
    intent.putExtra("reminder_text", editText.getText()
        .toString());
    setResult(Activity.RESULT_OK, intent);
    finish();
  }
}
