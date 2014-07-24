package com.allmycode.screen;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class ScreenActivity extends Activity {
  TextView textView;
  DisplayMetrics metrics;
  String densityDpiConstant;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    textView = (TextView) findViewById(R.id.textView1);

    metrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().
      getMetrics(metrics);

    switch (metrics.densityDpi) {
    case DisplayMetrics.DENSITY_LOW:
      densityDpiConstant = "DENSITY_LOW";
      break;
    case DisplayMetrics.DENSITY_MEDIUM:
      densityDpiConstant = "DENSITY_MEDIUM";
      break;
    case DisplayMetrics.DENSITY_HIGH:
      densityDpiConstant = "DENSITY_HIGH";
      break;
    default:
      densityDpiConstant = "Huh?";
      break;
    }

    textView.setText(metrics.toString()
        + ", " + densityDpiConstant);
  }
}
