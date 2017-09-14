package site.yanhui.coolweather.gson;

/**
 * Created by Archer on 2017/9/12.
 * <p>
 * 功能描述：
 * AQI 对应的映射
 *
 "aqi":{
 "city":{
 "aqi":"83",
 "co":"1",
 "no2":"45",
 "o3":"27",
 "pm10":"116",
 "pm25":"59",
 "qlty":"良",
 "so2":"67"
 }
 }
 */

public class AQI {
    public AQICity city;

    //内部类
    public class AQICity {
        public String aqi;
        public String pm25;
    }
}
