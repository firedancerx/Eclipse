package a.b.c;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
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

  public static final Uri SILLY_URI = Uri
      .parse("content://com.allmycode.db/silly_stuff");

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

    values.clear();
    values.put("amount", "500");
    resolver.update(CONTENT_URI, values, 
        "name='Sam'", null);

    addToTextView(CONTENT_URI);

    resolver.delete(CONTENT_URI, "1", null);

    addToTextView(CONTENT_URI);

    addToTextView(SILLY_URI);
  }

  void addToTextView(Uri uri) {
    cursor = resolver.query(uri, null, "1", null, null);
    startManagingCursor(cursor);

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
