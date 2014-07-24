package com.allmycode.frag;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class DocsActivityVerbose extends Activity {

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    setContentView(R.layout.docs_verbose);

    int index = getIntent().getIntExtra("index", 0);

    DocsFragmentVerbose docsFragmentVerbose =
        DocsFragmentVerbose.newInstance(index);        
    FragmentTransaction fragmentTransaction =
        getFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.docs_verbose_frame,
        docsFragmentVerbose);
    fragmentTransaction.setTransition
        (FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    fragmentTransaction.commit();
  }
}
