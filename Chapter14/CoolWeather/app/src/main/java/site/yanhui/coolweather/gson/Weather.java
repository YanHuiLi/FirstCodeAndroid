package site.yanhui.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Archer on 2017/9/12.
 * <p>
 * 功能描述：
 * 1.gson实体类
 * 2.总实体类来引用
 */

public class Weather {
    public String status;
    public  Basic basic;
    public  AQI aqi;
    public Now now;
    public  Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
