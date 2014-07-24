package com.allmycode.services;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class MyWeatherService extends Service {

  @Override
  public IBinder onBind(Intent intent) {
    doToast(R.string.service_bound);
    return new WeatherFetcher();
  }

  class WeatherFetcher extends WeatherInterface.Stub {
    public String fetchWeather(String city) {
      String weatherString = null;
      if (city != null) {
        Weather weather = getWeather(city);
        weatherString =
            Integer.toString(weather.getTemperature())
                + (char) 0x00B0 + "F "
                + weather.getCondition();
      }
      return weatherString;
    }
  }

  String getWeatherString(Message message) {
    Bundle query = message.getData();
    String location = query.getString("location");
    String weatherString;
    if (location != null && !location.equals("")) {
      Weather weather = getWeather(location);
      weatherString =
          Integer.toString(weather.getTemperature())
              + (char) 0x00B0 + "F "
              + weather.getCondition();
    } else {
      weatherString = "It's dark at night.";
    }
    return weatherString;
  }

  private static final String GOOGLE_WEATHER_URL =
      "http://www.google.com/ig/api?weather=";

  public Weather getWeather(String location) {
    URL url;
    Weather weather = null;
    try {
      url =
          new URL(GOOGLE_WEATHER_URL
              + location.replace(" ", "%20"));
      SAXParser parser =
          SAXParserFactory.newInstance().newSAXParser();
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

  @Override
  public boolean onUnbind(Intent intent) {
    doToast(R.string.service_stopped_itself);
    stopSelf();
    return false;
  }

  @Override
  public void onDestroy() {
    doToast(R.string.service_destroyed);
  }

  void doToast(int resource) {
    Toast.makeText(this, resource, Toast.LENGTH_SHORT)
        .show();
  }
}
