package com.example.my_weather1.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class hoursBean {
    @SerializedName("hour")
    private String hour;
    @SerializedName("wea")
    private String wea;
    @SerializedName("wea_img")
    private String wea_img;
    @SerializedName("tem")
    private String tem;

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
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

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }
}
