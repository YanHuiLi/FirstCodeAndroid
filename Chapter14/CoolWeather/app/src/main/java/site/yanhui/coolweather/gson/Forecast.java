package site.yanhui.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Archer on 2017/9/12.
 * <p>
 * 功能描述：
 * 单个天气实例，加进集合即可
 */

public class Forecast {
    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature{
        public String max;
        public String min;
    }



    public class More{
        @SerializedName("txt_d")
        public String info;
    }
}
