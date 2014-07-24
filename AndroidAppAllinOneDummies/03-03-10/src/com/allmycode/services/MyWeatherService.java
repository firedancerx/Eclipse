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

public class MyWeatherService extends Service {

	Messenger messengerToClient = null;

	MyIncomingHandler myIncomingHandler = new MyIncomingHandler();
	Messenger messengerToService = new Messenger(myIncomingHandler);

	@Override
	public IBinder onBind(Intent intent) {
		doToast(R.string.service_bound);
		return messengerToService.getBinder();
	}

	class MyIncomingHandler extends Handler {
		@Override
		public void handleMessage(Message incomingMessage) {
			messengerToClient = incomingMessage.replyTo;

			Bundle reply = new Bundle();
			reply.putString("weather", getWeatherString(incomingMessage));
			Message replyMessage = Message.obtain();
			replyMessage.setData(reply);
			try {
				messengerToClient.send(replyMessage);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			doToast(R.string.message_handled);
		}
	}

	@Override
	public boolean onUnbind(Intent intent) {
		doToast(R.string.service_stopped_itself);
		stopSelf();
		return false;
	}

	@Override
	public void onDestroy() {
		myIncomingHandler = null;
		doToast(R.string.service_destroyed);
	}

	void doToast(int resource) {
		Toast.makeText(this, resource, Toast.LENGTH_SHORT).show();
	}

	String getWeatherString(Message message) {
		Bundle query = message.getData();
		String location = query.getString("location");		
		
		String weatherString;
		if (location != null && !location.equals("")) {
			Weather weather = getWeather(location);
			weatherString = Integer.toString(weather.getTemperature())
					+ (char) 0x00B0 + "F " + weather.getCondition();
		} else {
			weatherString = "It's dark at night.";
		}
		return weatherString;
	}

	private static final String GOOGLE_WEATHER_URL = "http://www.google.com/ig/api?weather=";

	public Weather getWeather(String location) {
		URL url;
		Weather weather = null;
		try {
			url = new URL(GOOGLE_WEATHER_URL + location.replace(" ", "%20"));
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			XMLReader reader = parser.getXMLReader();
			MySaxHandler saxHandler = new MySaxHandler();
			reader.setContentHandler(saxHandler);
			reader.parse(new InputSource(url.openStream()));

			weather = saxHandler.getWeather();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return weather;
	}

}
