package site.yanhui.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Archer on 2017/9/12.
 * <p>
 * 功能描述：
 * 天气情况
 "now":{
 "cond":{
 "code":"101",
 "txt":"多云"
 },
 "fl":"13",
 "hum":"80",
 "pcpn":"0",
 "pres":"1026",
 "tmp":"16",
 "vis":"7",
 "wind":{
 "deg":"307",
 "dir":"西北风",
 "sc":"微风",
 "spd":"6"
 }
 }
 */

public class Now {
    @SerializedName("tmp")
    public String temprature;
    @SerializedName("cond")
    public More more;


    public class More{
        @SerializedName("text")
        public String info;
    }

}
