package com.allmycode.services;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

public class MyWeatherServiceOLD extends Service {

    private static final String GOOGLE_WEATHER_URL =
            "http://www.google.com/ig/api?weather=";
    // ///////BARRY - can have several clients -- keep track in arraylist
    Messenger incomingMessenger = null;
    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger replyMessenger = new Messenger(
            new IncomingHandler());

    public Weather getWeather(String location) {
        URL url;
        Weather weather = null;
        try {
            url =
               new URL(GOOGLE_WEATHER_URL
                         + location.replace(" ", "%20"));
            SAXParser parser = SAXParserFactory
                    .newInstance().newSAXParser();
            XMLReader reader = parser.getXMLReader();
            MySaxHandler handler = new MySaxHandler();
            reader.setContentHandler(handler);
            reader.parse
                    (new InputSource(url.openStream()));

            weather = handler.getWeather();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather;
    }

    /**
     * Handler of incoming messages from clients.
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            incomingMessenger = msg.replyTo;
            Bundle reply = new Bundle();

            Bundle query = msg.getData();
            String location = query.getString("location");
            if (location != null) {
                Weather weather = getWeather(location);
                String weatherString =
                        Integer.toString(weather
                                .getTemperature())
                                + (char) 0x00B0
                                + "F "
                                + weather.getCondition();
                reply.putString("weather", weatherString);
 
                Message replyMessage = Message.obtain();
                replyMessage.setData(reply);
                try {
                    incomingMessenger.send(replyMessage);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * When binding to the service, we return an interface to our messenger for
     * sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return replyMessenger.getBinder();
    }

    @Override
    public void onDestroy() {
 //       Toast.makeText(this, R.string.service_stopped,
   //             Toast.LENGTH_SHORT).show();
    }
}
