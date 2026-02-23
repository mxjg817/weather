package com.example.my_weather1.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Netutil {
    public static final String URL_WEATHER_SEVEN = "http://v1.yiketianqi.com/free/week?appid=85785165&appsecret=fa5CDaxl&unescape=1";
    public static final String URL_WEATHER_HOURS = "http://gfeljm.tianqiapi.com/free/v2030?city=&cityid=&adcode=130200000000&appid=85785165&appsecret=fa5CDaxl&lng=&lat=&aqi=&hours=";

    public static String doGet(String urlStr) {
        String result = "";
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        //连接网络
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            //从连接中读取数据二进制
            InputStream inputStream = connection.getInputStream();

            inputStreamReader = new InputStreamReader(inputStream);

            bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            result = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return result;
    }

    public static String getweather_seven(String city) {
        //拼接出获取天气数据的URL
        String weatherurl = URL_WEATHER_SEVEN + "&city=" + city;
        Log.d("fan1", "weatherURl" + weatherurl);
        String weatherResult = doGet(weatherurl);
        Log.d("fan1", "weatherResult " + weatherResult);
        return weatherResult;
    }

    public static String getweather_hours(String city) {
        //拼接出获取天气数据的URL
        String weatherurl = URL_WEATHER_HOURS + "&city=" + city;
        Log.d("fan2", "weatherURl" + weatherurl);
        String weatherResult = doGet(weatherurl);
        Log.d("fan2", "weatherResult " + weatherResult);
        return weatherResult;
    }


}
