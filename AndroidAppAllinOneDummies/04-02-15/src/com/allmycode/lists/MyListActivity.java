package com.allmycode.lists;

import java.util.ArrayList;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListActivity extends ListActivity {
  int notificationNumber = 0;
  NotificationManager notificationMgr;

  /* Code copied from Listing 2-12... */

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    String isChecked =
        intent.getData().getSchemeSpecificPart();

    ArrayList<Integer> listItems =
        new ArrayList<Integer>();
    for (int i = 0; i < 5; i++) {
      if (isChecked.charAt(i) == '1') {
        listItems.add(i);
      }
    }

    setListAdapter(new ArrayAdapter<Integer>(this,
        R.layout.my_list_layout, listItems));
  }

  /* Code to respond to a list item click... */

  @Override
  public void onListItemClick(ListView listView,
      View view, int position, long id) {

    makeNewNotification(listView, position);
  }

  /* Code to create a status bar notification... */

  private void makeNewNotification(ListView listView,
      int position) {

    String numberValue =
        ((Integer) listView.getItemAtPosition(position))
            .toString();

    Notification notification = new Notification();
    notification.icon =
        android.R.drawable.ic_menu_info_details;
    notification.flags = Notification.FLAG_AUTO_CANCEL;

    Intent intent =
        new Intent(this, YetAnotherActivity.class);
    intent.setData(Uri.parse("number:" + numberValue));
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    PendingIntent pendingIntent =
        PendingIntent.getActivity(this, 0, intent, 0);

    notification.setLatestEventInfo(this, "Look!",
        numberValue, pendingIntent);

    notificationMgr =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

    notificationMgr.notify(notificationNumber++,
        notification);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    for (int i = 0; i <= notificationNumber; i++) {
      notificationMgr.cancel(i);
    }
  }
}
