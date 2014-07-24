package com.allmycode.frag;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;

public class AllPurposeActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
  }
  
  @Override
  public void
      onConfigurationChanged(Configuration config) {

    super.onConfigurationChanged(config);

    if (config.orientation == 
        Configuration.ORIENTATION_PORTRAIT) {
      FragmentManager fragmentManager =
          getFragmentManager();
      if (fragmentManager.getBackStackEntryCount() > 1) {
        fragmentManager.popBackStackImmediate();
      }
    }
  }
}
