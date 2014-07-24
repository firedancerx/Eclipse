package com.allmycode.db1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class MyActivity extends Activity {

  TextView textView;
  Cursor cursor = null;
  DBHelper helper = null;
  SQLiteDatabase db = null;
  ContentValues values = null;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    textView = (TextView) findViewById(R.id.textView1);

    helper = new DBHelper(this, "simple_db", null, 1);
    db = helper.getWritableDatabase();
    values = new ContentValues();

    values.put("name", "Barry");
    values.put("amount", "100");
    db.insert("simpletable", "", values);
    values.clear();
    values.put("name", "Harriet");
    values.put("amount", "300");
    db.insert("simpletable", "", values);

    addToTextView();

    values.clear();
    values.put("amount", "500");
    db.update("simpletable", values, "name='Barry'", null);

    addToTextView();

    db.delete("simpletable", "1", null);

    addToTextView();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    helper.close();
  }

  void addToTextView() {
    cursor =
        db.rawQuery("SELECT * FROM simpletable;", null);

    if (cursor != null && cursor.moveToFirst()) {

      String name;
      do {
        String _id = cursor.getString(0);
        name = cursor.getString(1);
        int amount = cursor.getInt(2);
        textView.append(_id + " " + name + " " + amount
            + "\n");
      } while (cursor.moveToNext());
    }
    textView.append("-----------\n");
  }
}
