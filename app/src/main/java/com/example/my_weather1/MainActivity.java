package com.example.my_weather1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_weather1.bean.WeatherBean_hours;
import com.example.my_weather1.bean.hoursBean;
import com.google.gson.Gson;

import java.util.List;

import com.example.my_weather1.bean.DayWeatehrBean;
import com.example.my_weather1.bean.WeatherBean;
import com.example.my_weather1.util.Netutil;

public class MainActivity extends AppCompatActivity {


    private AppCompatSpinner mSpinner;

    private ArrayAdapter<String> mspAdapter;
    private String[] mCities;
    private TextView tvweather, tvwin, tvtem, tv_air, tvpressure, tv_vissible, tvhumidity;

    private ImageView imageView;
    private RecyclerView rlvfutureweather;
    private Future_Weather mweatherAdapter;
    private EditText etCity;
    private Button btnSearch;
    private Handler mhandler = new Handler(Looper.myLooper()) {
        @Override
        public void dispatchMessage(@NonNull Message msg) {
            super.dispatchMessage(msg);
            WeatherBean weatherBean = null;
            WeatherBean_hours weatherBean_hours = null;
            switch (msg.what) {
                case 0:
                    String weather = (String) msg.obj;
                    Log.d("fan", "主线程收到了天气预报" + weather);

                    Gson gson = new Gson();
                    weatherBean = gson.fromJson(weather, WeatherBean.class);
                    Log.d("fan", "解析后的数据" + weather.toString());
                    updateUi(weatherBean);
                    break;

                case 1:
                    String weather_hours = (String) msg.obj;
                    Log.d("fan1", "主线程收到了天气预报" + weather_hours);

                    Gson gson_hours = new Gson();
                    weatherBean_hours = gson_hours.fromJson(weather_hours, WeatherBean_hours.class);
                    Log.d("fan1", "解析后的数据" + weather_hours.toString());
                    updateUi_hours(weatherBean_hours);
                    break;

            }
        }
    };

    /**
     * 更新ui
     */
    private void updateUi(WeatherBean weatherBean) {
        if (weatherBean == null) {
            return;
        }
        List<DayWeatehrBean> dayWeathers = weatherBean.getDayWeathers();
        DayWeatehrBean todayWeather = dayWeathers.get(0);
        if (todayWeather == null) {
            return;
        }

        tvtem.setText(todayWeather.getTem_night() + "℃ - " + todayWeather.getTem_day() + "℃");
        tvweather.setText(todayWeather.getWea() + "(" + todayWeather.getDate() + ")");
        tvwin.setText(todayWeather.getWin() + todayWeather.getWin_speed());
        imageView.setImageResource(getImg(todayWeather.getWea_img()));

        dayWeathers.remove(0);
        mweatherAdapter = new Future_Weather(this, dayWeathers);
        rlvfutureweather.setAdapter(mweatherAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rlvfutureweather.setLayoutManager(layoutManager);
        if (todayWeather.getWea_img() != null) {
            int bgimg = getbackground(todayWeather.getWea_img());
            findViewById(R.id.root_layout).setBackgroundResource(bgimg);
        } else {
            findViewById(R.id.root_layout).setBackgroundResource(R.drawable.bg_qing);
        }

    }

    private void updateUi_hours(WeatherBean_hours weatherBean_hours) {

        if (weatherBean_hours == null) {
            Log.d("fan", "weatherBean_hours 为 null，提前返回");
            return;
        }
        tv_air.setText(weatherBean_hours.getAir_level());
        tvpressure.setText(weatherBean_hours.getPressure());
        tvhumidity.setText(weatherBean_hours.getHumidly());
        tv_vissible.setText(weatherBean_hours.getVisibility());


    }

    /**
     *根据天气更换背景图，获取天气对应的图片
     */
    private int getbackground(String weaStr) {
        int result = 0;
        switch (weaStr) {
            case "qing":
                result = R.drawable.bg_qing;
                break;
            case "yin":
                result = R.drawable.bg_yin;
                break;
            case "yu":
                result = R.drawable.bg_yu;
                break;
            case "yun":
                result = R.drawable.bg_yin;
                break;
            case "bingbao":
                result = R.drawable.bg_xue;
                break;
            case "xue":
                result = R.drawable.bg_xue;
                break;


        }


        return result;
    }
    /**
     *获取天气对应的图片
     */
    private int getImg(String weaStr) {
        int result = 0;
        switch (weaStr) {
            case "qing":
                result = R.drawable.qing;
                break;
            case "yin":
                result = R.drawable.cloud;
                break;
            case "yu":
                result = R.drawable.yu;
                break;
            case "yun":
                result = R.drawable.yun;
                break;
            case "bingbao":
                result = R.drawable.bingbao;
                break;
            case "xue":
                result = R.drawable.xue;
                break;


        }


        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();
        /**
         *设置搜索点击事件
         */
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = etCity.getText().toString().trim();
                if (city.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入城市名", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 调用搜索方法

                searchCity(city);
            }

        });
    }
    /**
     *搜索方法
     */
    private void searchCity(String city) {
        /**
         *启动线程请求第一个api
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = Netutil.getweather_seven(city);
                Message msg = Message.obtain();
                msg.what = 0;
                msg.obj = result;
                mhandler.sendMessage(msg);
            }
        }).start();
/**
 *启动第二个
 */
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = Netutil.getweather_hours(city);
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = result;
                mhandler.sendMessage(msg);
            }
        }).start();
    }

    @SuppressLint("WrongViewCast")
    /**
     *初始化各种控件
     */
    private void initView() {
        mSpinner = findViewById(R.id.spinner_city);
        mCities = getResources().getStringArray(R.array.cities);
        mspAdapter = new ArrayAdapter<>(this, R.layout.sp_layout, mCities);
        mSpinner.setAdapter(mspAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectcity = mCities[position];
                getCityW_seven(selectcity);
                getCityW_hours(selectcity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tv_air = findViewById(R.id.tv_air);
        tvtem = findViewById(R.id.tv_tem);
        tvweather = findViewById(R.id.tv_weather);
        tvwin = findViewById(R.id.tv_win);
        imageView = findViewById(R.id.image);
        rlvfutureweather = findViewById(R.id.future);
        tvhumidity = findViewById(R.id.humidity);
        tvpressure = findViewById(R.id.pressure);
        tv_vissible = findViewById(R.id.visible);
        etCity = findViewById(R.id.et_city);
        btnSearch = findViewById(R.id.btn_search);

    }

    private void getCityW_seven(String selectcity) {
        //开启子线程
        new Thread(new Runnable() {
            public void run() {
                //请求网络
                String cityWeather = Netutil.getweather_seven(selectcity);
                //使用handler将数据传给主线程
                Message message = Message.obtain();
                message.what = 0;
                message.obj = cityWeather;
                mhandler.sendMessage(message);
            }
        }).start();

    }

    private void getCityW_hours(String selectcity) {
        //开启子线程
        new Thread(new Runnable() {
            public void run() {
                Log.d("fan3", "getSecondApiData called with param: " + selectcity);
                //请求网络
                String cityWeather = Netutil.getweather_hours(selectcity);
                //使用handler将数据传给主线程
                Message message = Message.obtain();
                message.what = 1;
                message.obj = cityWeather;
                mhandler.sendMessage(message);
            }
        }).start();

    }

}