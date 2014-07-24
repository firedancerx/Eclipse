package com.allmycode.services;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySaxHandler extends DefaultHandler {

  private static final String CURRENT_CONDITIONS =
      "current_conditions";
  private static final String DATA = "data";
  private static final String CONDITION = "condition";
  private static final String TEMP_F = "temp_f";

  private Weather weather = new Weather();

  private boolean current_conditions = false;

  public Weather getWeather() {
    return weather;
  }

  @Override
  public void startElement(String namespaceURI,
      String localName, String qName,
      Attributes attributes) throws SAXException {

    if (localName.equals(CURRENT_CONDITIONS)) {
      current_conditions = true;
    } else {
      if (current_conditions) {
        if (localName.equals(TEMP_F)) {
          String dataAttribute =
              attributes.getValue(DATA);
          weather.setTemperature(Integer
              .parseInt(dataAttribute));
        } else if (localName.equals(CONDITION)) {
          String condAttribute =
              attributes.getValue(DATA);
          weather.setCondition(condAttribute);
        }
      }
    }
  }

  @Override
  public void endElement(String namespaceURI,
      String localName, String qName) throws SAXException {
    if (localName.equals(CURRENT_CONDITIONS)) {
      current_conditions = false;
    }
  }
}
