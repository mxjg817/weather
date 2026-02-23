package com.example.my_weather1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.my_weather1.bean.DayWeatehrBean;

public class Future_Weather extends RecyclerView.Adapter<Future_Weather.WeatherViewHolder> {

    private Context mcontext;

    private List<DayWeatehrBean> mWeatherBean;

    public Future_Weather(Context context, List<DayWeatehrBean> weatherBeans){
        mcontext = context;
        this.mWeatherBean = weatherBeans;
    }
    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.weather_layout,parent,false);
        WeatherViewHolder weatherViewHolder = new WeatherViewHolder(view);


        return weatherViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {

        DayWeatehrBean weatehrBean = mWeatherBean.get(position);
        holder.tvWeather.setText(weatehrBean.getDate());
        holder.tvtem.setText(weatehrBean.getTem_day() + "℃ - " + weatehrBean.getTem_night() + "℃");
        holder.imageView.setImageResource(getImg(weatehrBean.getWea_img()));
    }

    @Override
    public int getItemCount() {
        if(mWeatherBean == null){
            return 0;
        }
        return mWeatherBean.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder{

        TextView tvWeather,tvtem;
        ImageView imageView;
        public WeatherViewHolder (@NonNull View itemView){
            super(itemView);

            tvWeather = itemView.findViewById(R.id.tv_weather);
            tvtem = itemView.findViewById(R.id.tv_tem);

            imageView = itemView.findViewById(R.id.image);
        }

    }
    private int getImg(String weaStr){
        int result = 0;
        switch (weaStr){
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
}
