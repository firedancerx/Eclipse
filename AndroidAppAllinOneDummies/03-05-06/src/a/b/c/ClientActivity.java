package a.b.c;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.Loader.OnLoadCompleteListener;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class ClientActivity extends Activity {
  TextView textView;
  Cursor cursor = null;
  ContentValues values = null;
  ContentResolver resolver = null;

  public static final Uri CONTENT_URI = Uri
      .parse("content://com.allmycode.db/names_amounts");

  @Override
  public void onCreate(Bundle b) {
    super.onCreate(b);
    setContentView(R.layout.main);

    textView = (TextView) findViewById(R.id.textView1);

    values = new ContentValues();
    resolver = getContentResolver();

    values.put("name", "Sam");
    values.put("amount", "100");
    resolver.insert(CONTENT_URI, values);
    values.clear();
    values.put("name", "Jennie");
    values.put("amount", "300");
    resolver.insert(CONTENT_URI, values);

    addToTextView(CONTENT_URI);
  }

  CursorLoader loader = null;

  void addToTextView(Uri uri) {
    loader =
        new CursorLoader(this, uri, null, "1", null, null);
    loader.registerListener(42,
        new MyOnLoadCompleteListener());
    loader.startLoading();
    textView.append("Here it comes...\n");
  }

  class MyOnLoadCompleteListener implements
      OnLoadCompleteListener<Cursor> {

    @Override
    public void onLoadComplete(Loader<Cursor> loader,
        Cursor cursor) {
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
}
