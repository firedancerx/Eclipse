package com.allmycode.demos;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.allmycode.services.WeatherInterface;

public class ServiceConsumerActivity extends Activity
    implements OnClickListener {

  WeatherInterface reporter;

  ServiceConnection connection =
      new MyServiceConnection();
  SharedPreferences prefs;
  boolean isBound = false;

  void bind() {
    Intent intent = new Intent();
    intent.setAction("com.allmycode.WEATHER");
    isBound =
        bindService(intent, connection,
            Context.BIND_AUTO_CREATE);
  }

  public void queryService() {
    if (isBound) {
      try {
        String report =
            reporter.fetchWeather(locationText.getText()
                .toString());
        textView1.setText(report);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    } else {
      textView1.setText(R.string.service_not_bound);
    }
  }

  void unbind() {
    if (isBound) {
      unbindService(connection);
      isBound = false;
    }
  }

  class MyServiceConnection implements ServiceConnection {
    public void onServiceConnected(
        ComponentName className, IBinder binder) {

      reporter =
          WeatherInterface.Stub.asInterface(binder);

      doToast(R.string.service_connected);
    }

    public void onServiceDisconnected(ComponentName n) {
      doToast(R.string.service_crashed);
    }
  }

  void doToast(int resource) {
    Toast.makeText(this, resource, Toast.LENGTH_SHORT)
        .show();
  }

  @Override
  public void onDestroy() { 
    super.onDestroy();
    prefs = getSharedPreferences("PREFS", MODE_PRIVATE);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putBoolean("isBound", isBound);
    editor.putString("report", textView1.getText()
        .toString());
    editor.commit();
    unbind();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    prefs = getSharedPreferences("PREFS", MODE_PRIVATE);
    if (prefs != null) {
      textView1 = (TextView) findViewById(R.id.textView1);

      textView1.setText(prefs.getString("report",
        getString(R.string.report_appears_here)));
      if (prefs.getBoolean("isBound", false)) {
        bind();
      }
    }


    // THE REST IS BOILERPLATE...

    bindButton = (Button) findViewById(R.id.buttonBind);
    bindButton.setOnClickListener(this);
    locationText =
        (EditText) findViewById(R.id.editText1);
    queryButton = (Button) findViewById(R.id.buttonQuery);
    queryButton.setOnClickListener(this);
    unbindButton =
        (Button) findViewById(R.id.buttonUnbind);
    unbindButton.setOnClickListener(this);
  }

  public static TextView textView1;
  Button bindButton, queryButton, unbindButton;
  EditText locationText;

  @Override
  public void onClick(View view) {
    if (view == bindButton) {
      bind();
    } else if (view == queryButton) {
      queryService();
    } else if (view == unbindButton) {
      unbind();
    }
  }
}
