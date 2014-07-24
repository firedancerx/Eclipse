package com.allmycode.services;

public class Weather {

  private int temperature = 0;
  private String condition = "";

  public Weather() {
  }

  public Integer getTemperature() {
    return temperature;
  }

  public void setTemperature(Integer temperature) {
    this.temperature = temperature;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

}
