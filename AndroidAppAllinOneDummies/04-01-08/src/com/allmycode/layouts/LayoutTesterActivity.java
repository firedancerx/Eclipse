package com.allmycode.layouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LayoutTesterActivity extends Activity {
  ImageView imageCalico, imageBurmese;
  TextView textView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.frame);
    imageCalico =
        (ImageView) findViewById(R.id.imageViewCalico);
    imageBurmese =
        (ImageView) findViewById(R.id.imageViewBurmese);
    textView = (TextView) findViewById(R.id.textView);
  }

  int count = 0;

  public void rotate(View view) {
    switch (count++ % 3) {
    case 0:
      textView.setVisibility(View.VISIBLE);
      imageCalico.setVisibility(View.INVISIBLE);
      imageBurmese.setVisibility(View.INVISIBLE);
      break;
    case 1:
      textView.setVisibility(View.INVISIBLE);
      imageCalico.setVisibility(View.VISIBLE);
      imageBurmese.setVisibility(View.INVISIBLE);
      break;
    case 2:
      textView.setVisibility(View.INVISIBLE);
      imageCalico.setVisibility(View.INVISIBLE);
      imageBurmese.setVisibility(View.VISIBLE);
      break;
    }
  }
}
