package com.allmycode.lists;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends Activity {
    CheckBox[] checkBoxes = new CheckBox[5];
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        checkBoxes[0] = (CheckBox) findViewById(R.id.a);
        checkBoxes[1] = (CheckBox) findViewById(R.id.b);
        checkBoxes[2] = (CheckBox) findViewById(R.id.c);
        checkBoxes[3] = (CheckBox) findViewById(R.id.d);
        checkBoxes[4] = (CheckBox) findViewById(R.id.e);
    }
    
    public void onShowListClick(View view) {
      Intent intent =
          new Intent(this, MyListActivity.class);

      StringBuffer isChecked = new StringBuffer("");

      for (CheckBox box : checkBoxes) {
        isChecked.append(box.isChecked() ? "1" : "0");
      }
      intent.setData(Uri.parse("checked:"
          + isChecked.toString()));

      startActivity(intent);
    }
}
