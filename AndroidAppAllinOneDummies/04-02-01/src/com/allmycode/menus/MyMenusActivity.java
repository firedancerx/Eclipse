package com.allmycode.menus;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyMenusActivity extends Activity {
  ListView listView;
  ArrayList<String> listItems = new ArrayList<String>();
  ArrayAdapter<String> adapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    listView = (ListView) findViewById(R.id.listView1);
    adapter =
        new ArrayAdapter<String>(this,
            R.layout.my_list_layout, listItems);
    listView.setAdapter(adapter);

    registerForContextMenu(listView);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);

    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.list_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {
    
    case R.id.menu_insert:
      createReminder();
      return true;
      
    case R.id.make_noise:
      MediaPlayer mediaPlayer =
          MediaPlayer.create(this, R.raw.honk);
      mediaPlayer.start();
      return true;
      
    }
    return super.onOptionsItemSelected(item);
  }

  private static final int ACTIVITY_CREATE = 0;

  private void createReminder() {
    Intent intent =
        new Intent(this, ReminderEditActivity.class);
    startActivityForResult(intent, ACTIVITY_CREATE);
  }

  @Override
  protected void onActivityResult(int requestCode,
      int resultCode, Intent intent) {
    if (resultCode == RESULT_OK) {
      listItems.add(intent
          .getStringExtra("reminder_text"));
      adapter.notifyDataSetChanged();
    }
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu,
      View view, ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, view, menuInfo);
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.list_menu_item_longpress,
        menu);
  }

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    
    switch (item.getItemId()) {
    
    case R.id.menu_delete:
      deleteReminder(item);
      return true;
      
    case R.id.make_noise2:
      MediaPlayer mediaPlayer =
          MediaPlayer.create(this, R.raw.quack);
      mediaPlayer.start();
      return true;
      
    }
    return super.onContextItemSelected(item);
  }

  void deleteReminder(MenuItem item) {
    AdapterContextMenuInfo info =
        (AdapterContextMenuInfo) item.getMenuInfo();
    listItems.remove(info.position);
    adapter.notifyDataSetChanged();
  }

}