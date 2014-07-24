package com.allmycode.frag;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DocsFragment extends Fragment {

  int index;

  public static DocsFragment newInstance(int index) {
    DocsFragment docsFragment = new DocsFragment();
    docsFragment.index = index;
    return docsFragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {

    LinearLayout layout = new LinearLayout(getActivity());
    layout.setOrientation(LinearLayout.VERTICAL);
    TextView textView1 = new TextView(getActivity());
    textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
    textView1
        .setText(ComponentNamesFragment.COMPONENTS[index]);
    layout.addView(textView1);
    TextView textView2 = new TextView(getActivity());
    textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
    textView2.setText(DOCS[index]);
    layout.addView(textView2);

    Button button = new Button(getActivity());
    button.setText("More");
    button
        .setOnClickListener(new MyButtonListener(index));
    layout.addView(button);

    return layout;
  }

  public class MyButtonListener implements
      OnClickListener {
    int index;

    public MyButtonListener(int index) {
      this.index = index;
    }

    @Override
    public void onClick(View view) {
      if (getResources().getConfiguration().orientation 
                == Configuration.ORIENTATION_LANDSCAPE) {

        DocsFragmentVerbose docsFragmentVerbose =
            DocsFragmentVerbose.newInstance(index);
        FragmentTransaction fragmentTransaction =
            getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.docs,
            docsFragmentVerbose);
        fragmentTransaction.setTransition
            (FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
      } else {
        Intent intent = new Intent();
        intent.setClass(getActivity(),
            DocsActivityVerbose.class);
        intent.putExtra("index", index);
        startActivity(intent);
      }
    }
  }

  final static String[] DOCS =
    {"An activity is a single, focused thing that the"
     + " user can do. Almost all activities interact"
     + " with the user, so the Activity class takes"
     + " care of creating a window for you in which you"
     + " can place your UI with setContentView(View).",
       "A Service is an application component"
     + " representing either an application's desire"
     + " to perform a longer-running operation while"
     + " not interacting with the user or to supply"
     + " functionality for other applications to use.",
       "Base class for code that will receive intents"
     + " sent by sendBroadcast(). You can either"
     + " dynamically register an instance of this class"
     + " with Context.registerReceiver() or statically"
     + " publish an implementation through the"
     + " <receiver> tag in your AndroidManifest.xml.",
       "Content providers are one of the primary"
     + " building blocks of Android applications,"
     + " providing content to applications. They"
     + " encapsulate data and provide it to applications"
     + " through the single ContentResolver interface."
     + " A content provider is only required if you need"
     + " to share data between multiple applications." 
    };
}
