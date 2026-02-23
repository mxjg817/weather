package com.example.my_weather1.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherBean_hours {
    @SerializedName("date")
    private String date;
    @SerializedName("wea")
    private String wea;


    @SerializedName("wea_img")
    private String wea_img;

    @SerializedName("win")
    private String win;

    @SerializedName("win_speed")
    private String win_speed;

    @SerializedName("humidly")
    private String humidly;

    @SerializedName("visibility")
    private String visibility;

    @SerializedName("pressure")
    private String pressure;

    @SerializedName("air")
    private String air;

    @SerializedName("air_level")
    private String air_level;

    @SerializedName("air_tips")
    private String air_tips;

    @SerializedName("alarm")
    private List<alarmBean> alarms;

    @SerializedName("hours")
    private List<hoursBean> hour;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getWea_img() {
        return wea_img;
    }

    public void setWea_img(String wea_img) {
        this.wea_img = wea_img;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getWin_speed() {
        return win_speed;
    }

    public void setWin_speed(String win_speed) {
        this.win_speed = win_speed;
    }

    public String getHumidly() {
        return humidly;
    }

    public void setHumidly(String humidly) {
        this.humidly = humidly;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getAir_level() {
        return air_level;
    }

    public void setAir_level(String air_level) {
        this.air_level = air_level;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getAir_tips() {
        return air_tips;
    }

    public void setAir_tips(String air_tips) {
        this.air_tips = air_tips;
    }

    public List<alarmBean> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<alarmBean> alarms) {
        this.alarms = alarms;
    }

    public List<hoursBean> getHours() {
        return hour;
    }

    public void setHours(List<hoursBean> hours) {
        this.hour = hour;
    }
}
