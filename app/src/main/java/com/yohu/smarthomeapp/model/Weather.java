package com.yohu.smarthomeapp.model;

public class Weather {
    private String city;
    private String temperature;
    private String weather;
    private String dressingAdvice;
    private String currentTemperature;
    private String image;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDressingAdvice() {
        return dressingAdvice;
    }

    public void setDressingAdvice(String dressingAdvice) {
        this.dressingAdvice = dressingAdvice;
    }

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
