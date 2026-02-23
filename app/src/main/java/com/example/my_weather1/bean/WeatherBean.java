package com.example.my_weather1.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherBean {
    @SerializedName("cityid")
    private String cityed;

    @SerializedName("city")
    private String city;

    @SerializedName("update_time")
    private String update_time;

    @SerializedName("data")
   private List<DayWeatehrBean> dayWeathers;

    public String getCityed() {
        return cityed;
    }

    public void setCityed(String cityed) {
        this.cityed = cityed;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public List<DayWeatehrBean> getDayWeathers() {
        return dayWeathers;
    }

    public void setDayWeathers(List<DayWeatehrBean> dayWeathers) {
        this.dayWeathers = dayWeathers;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "cityed='" + cityed + '\'' +
                ", city='" + city + '\'' +
                ", update_time='" + update_time + '\'' +
                ", dayWeathers=" + dayWeathers +
                '}';
    }
}
