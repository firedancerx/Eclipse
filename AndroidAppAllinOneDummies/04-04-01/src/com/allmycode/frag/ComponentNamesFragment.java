package com.allmycode.frag;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ComponentNamesFragment extends ListFragment {

  final static String[] COMPONENTS = { "Activity",
      "Service", "BroadcastReceiver", "ContentProvider" };

  @Override
  public void onActivityCreated
                             (Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    setListAdapter(new ArrayAdapter<String>(
        getActivity(),
        android.R.layout.simple_list_item_activated_1,
        COMPONENTS));
  }

  @Override
  public void onListItemClick(ListView l, View v,
      int index, long id) {

    getListView().setItemChecked(index, true);
    DocsFragment docsFragment =
        DocsFragment.newInstance(index);
    FragmentManager fragmentManager =
        getFragmentManager();
    FragmentTransaction fragmentTransaction =
        fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.docs, docsFragment);
    int backStackEntryCount =
        fragmentManager.getBackStackEntryCount();
    for (int i = 0; i < backStackEntryCount; i++) {
      fragmentManager.popBackStackImmediate();
    }
    fragmentTransaction.addToBackStack(null);

    fragmentTransaction.commit();
  }
}
