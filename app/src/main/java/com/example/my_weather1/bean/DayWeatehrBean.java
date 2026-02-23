package com.example.my_weather1.bean;

import com.google.gson.annotations.SerializedName;

public class DayWeatehrBean {
    @SerializedName("date")
    private String date;
    @SerializedName("wea")
    private String wea;


    @SerializedName("wea_img")
    private String wea_img;

    @SerializedName("tem_night")
    private String tem_night;

    @SerializedName("tem_day")
    private String tem_day;

    @SerializedName("win")
    private String win;

    @SerializedName("win_speed")
    private String win_speed;

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

    public String getTem_night() {
        return tem_night;
    }

    public void setTem_night(String tem_night) {
        this.tem_night = tem_night;
    }

    public String getTem_day() {
        return tem_day;
    }

    public void setTem_day(String tem_day) {
        this.tem_day = tem_day;
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
}
