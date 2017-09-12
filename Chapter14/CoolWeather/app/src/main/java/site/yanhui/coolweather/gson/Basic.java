package site.yanhui.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Archer on 2017/9/11.
 * <p>
 * 功能描述：
 * 对应basic的json实体类,Gson最牛的一点就是能把jsonobject映射成为对象，方便直接使用。
 *
 "basic":{
 "city":"昆明",
 "cnty":"中国",
 "id":"CN101290101",
 "lat":"25.04060936",
 "lon":"102.71224976",

 "update":{
 "loc":"2017-09-12 07:46",
 "utc":"2017-09-11 23:46"
 }
 */
//抽取几个有效字段来做就行了
public class Basic {
    @SerializedName("city")
    public  String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {
        @SerializedName("loc")
        public String updateTime;

    }
}
