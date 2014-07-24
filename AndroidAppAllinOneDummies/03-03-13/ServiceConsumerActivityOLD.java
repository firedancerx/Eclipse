package com.allmycode.demos;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceConsumerActivityOLD extends Activity
        implements OnClickListener {
    public static TextView textView1;
    Button bindButton, queryButton, unbindButton;
    EditText locationText;
    Messenger messengerOut = null;
    Messenger messengerIn = 
            new Messenger(new IncomingHandler());
    boolean isBound;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bindButton =
                (Button) findViewById(R.id.buttonBind);
        bindButton.setOnClickListener(this);
        locationText =
                (EditText) findViewById(R.id.editText1);
        queryButton =
                (Button) findViewById(R.id.buttonQuery);
        queryButton.setOnClickListener(this);
        textView1 =
                (TextView) findViewById(R.id.textView1);
        unbindButton =
                (Button) findViewById(R.id.buttonUnbind);
        unbindButton.setOnClickListener(this);
        setButtonsEnabled(true, false, false);
    }

    void setButtonsEnabled(boolean bind, 
                           boolean query,
                           boolean unbind) {
        bindButton.setEnabled(bind);
        queryButton.setEnabled(query);
        unbindButton.setEnabled(unbind);
    }

    @Override
    public void onClick(View view) {
        if (view == bindButton) {
            bindService();
        } else if (view == queryButton) {
            queryService();
        } else if (view == unbindButton) {
            unbindService();
        }
    }

    void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.allmycode.WEATHER");
        bindService
          (intent, connection, Context.BIND_AUTO_CREATE);
        isBound = true;
        setButtonsEnabled(false, true, true);
    }

    void unbindService() {
        if (isBound) {
            if (messengerOut != null) {
                try {
                    Message msg = Message.obtain();
                    msg.replyTo = messengerIn;
                    messengerOut.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            unbindService(connection);
            isBound = false;

            setButtonsEnabled(true, false, false);
        }
    }

    public void queryService() {
        // BARRY - can't marshall non-parcellable objects across processes
        // MyWeatherServiceOLD service = new MyWeatherServiceOLD();
        Bundle bundle = new Bundle();
        bundle.putString
          ("location", locationText.getText().toString());

        Message msg = Message.obtain();
        msg.replyTo = messengerIn;
        msg.setData(bundle);
        try {
            messengerOut.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            textView1
                    .setText(bundle.getString("weather"));
        }
    }

    private ServiceConnection connection =
            new ServiceConnection() {
                public void onServiceConnected(
                        ComponentName className,
                        IBinder service) {

                    messengerOut = new Messenger(service);
                    Toast.makeText(
                            ServiceConsumerActivityOLD.this,
                            R.string.service_connected,
                            Toast.LENGTH_SHORT).show();
                }

                public void onServiceDisconnected(
                        ComponentName className) {
                    // This is called when the connection with the service has
                    // been
                    // unexpectedly disconnected -- that is, its process
                    // crashed.
                    messengerOut = null;

                    Toast.makeText(
                            ServiceConsumerActivityOLD.this,
                            R.string.service_disconnected,
                            Toast.LENGTH_SHORT).show();
                }
            };

    // ////////////
    // BARRY
    // startService() Every call to this method will result in a corresponding
    // call to the target service's onStartCommand(Intent, int, int) method,
    // with the intent given here.
    // This provides a convenient way to submit jobs to a service without having
    // to bind and call on to its interface.
    // BUT IT LOOKS AS IF YOU CAn't get info back from the service unless you
    // BIND
}