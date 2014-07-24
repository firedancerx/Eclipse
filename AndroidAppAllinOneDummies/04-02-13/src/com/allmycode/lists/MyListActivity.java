package com.allmycode.lists;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class MyListActivity extends ListActivity {

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    String isChecked =
        intent.getData().getSchemeSpecificPart();

    ArrayList<HashMap<String, String>> data =
        new ArrayList<HashMap<String, String>>();

    for (int i = 0; i < 5; i++) {
      if (isChecked.charAt(i) == '1') {
        HashMap<String, String> map =
            new HashMap<String, String>();
        map.put("number", Integer.toString(i));
        map.put("letter",
            (new Character((char) (i + 65))).toString());
        data.add(map);
      }
    }

    String[] columnNames = { "number", "letter" };
    int[] textViews = new int[2];
    textViews[0] = R.id.numberView;
    textViews[1] = R.id.letterView;

    setListAdapter(new SimpleAdapter(this, data,
        R.layout.my_list_layout, columnNames, textViews));
  }

  static final String
      letterToIntegerString(String letter) {
    return Integer
        .toString((int) (letter.charAt(0) - 65));
  }
}
