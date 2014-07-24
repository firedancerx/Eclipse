package com.allmycode.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class MyWeatherService extends Service {

  Messenger messengerToClient = null;

  MyIncomingHandler myIncomingHandler =
      new MyIncomingHandler();
  Messenger messengerToService = 
      new Messenger(myIncomingHandler);

  @Override
  public IBinder onBind(Intent intent) {
    return messengerToService.getBinder();
  }

  class MyIncomingHandler extends Handler {
    @Override
    public void handleMessage(Message incomingMessage) {
      messengerToClient = incomingMessage.replyTo;

      Bundle reply = new Bundle();
      reply.putString("weather", "It's dark at night.");
      Message replyMessage = Message.obtain();
      replyMessage.setData(reply);
      try {
        messengerToClient.send(replyMessage);
      } catch (RemoteException e) {
        e.printStackTrace();
      }
    }
  }
}
