package com.allmycode.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

  public DBHelper(Context context, String dbName,
      SQLiteDatabase.CursorFactory factory, int version) {
    super(context, dbName, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String createString =
        "CREATE TABLE IF NOT EXISTS simpletable "
            + "( _id INTEGER PRIMARY KEY autoincrement, "
            + "name TEXT NOT NULL, "
            + "amount INTEGER NOT NULL);";
    db.execSQL(createString);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db,
      int oldVersion, int newVersion) {
    String dropString =
        "DROP TABLE IF EXISTS simpletable";
    db.execSQL(dropString);
    onCreate(db);
  }
}
