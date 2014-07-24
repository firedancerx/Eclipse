package com.allmycode.frag;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class DocsFragmentVerbose extends Fragment {
  
  int index;

  public static DocsFragmentVerbose
      newInstance(int index) {
    DocsFragmentVerbose docsFragmentVerbose =
        new DocsFragmentVerbose();
    docsFragmentVerbose.index = index;
    return docsFragmentVerbose;
  }

  @Override
  public View onCreateView(LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {

    WebView webView = new WebView(getActivity());

    webView.loadUrl(
        "http://developer.android.com/reference/android/"
            + ((index < 2) ? "app/" : "content/")
            + ComponentNamesFragment.COMPONENTS[index]
            + ".html");
    
    return webView;
  }
}
