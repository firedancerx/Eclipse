package com.allmycode.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
  public static final Uri CONTENT_URI = Uri
      .parse("content://com.allmycode.db/names_amounts");
  public static final Uri SILLY_URI = Uri
      .parse("content://com.allmycode.db/silly_stuff");

  private static final String SIMPLE_DB = "simple_db";
  private static final String SIMPLETABLE = "simpletable";

  Cursor cursor = null;
  DBHelper helper = null;
  SQLiteDatabase db = null;
  ContentValues values = null;
  UriMatcher uriMatcher = null;
  {
    uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    uriMatcher.addURI("com.allmycode.db",
                                 "names_amounts", 1);
    uriMatcher.addURI("com.allmycode.db", 
                                   "silly_stuff", 2);
  }

  @Override
  public boolean onCreate() {
    try {
      helper =
          new DBHelper(getContext(), SIMPLE_DB, null, 1);
      db = helper.getWritableDatabase();
      values = new ContentValues();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public Uri insert(Uri ure, ContentValues values) {
    long id = db.insert(SIMPLETABLE, "", values);
    return ContentUris.withAppendedId(CONTENT_URI, id);
  }

  @Override
  public int update(Uri uri, ContentValues values,
      String whereClause, String[] whereArgs) {

    int numOfChangedRows =
        db.update(SIMPLETABLE, values, whereClause,
            whereArgs);

    return numOfChangedRows;
  }

  @Override
  public int delete(Uri uri, String whereClause,
      String[] whereArgs) {
    int numOfChangedRows =
        db.delete(SIMPLETABLE, whereClause, whereArgs);
    return numOfChangedRows;
  }

  @Override
  public Cursor query(Uri uri, String[] columns,
      String whereClause, String[] whereArgs,
      String sortOrder) {
    Cursor cursor = null;
    int code = uriMatcher.match(uri);
    if (code == 1) {

      cursor =
          db.query(SIMPLETABLE, columns, whereClause,
              whereArgs, null, null, sortOrder);

    } else if (code == 2) {
      String[] columnNames = { "_id", "name", "amount" };
      String[] rowValues = { "Table ", "4 ", "2" };
      MatrixCursor matrixCursor =
          new MatrixCursor(columnNames);
      matrixCursor.addRow(rowValues);
      cursor = matrixCursor;
    }
    return cursor;
  }

  @Override
  public String getType(Uri uri) {
    return null;
  }
}
