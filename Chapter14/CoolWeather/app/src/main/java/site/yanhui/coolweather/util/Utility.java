package site.yanhui.coolweather.util;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import site.yanhui.coolweather.db.City;
import site.yanhui.coolweather.db.County;
import site.yanhui.coolweather.db.Province;
import site.yanhui.coolweather.gson.Weather;

/**
 * Created by Archer on 2017/9/11.
 * <p>
 * 功能描述：
 * 解析服务器返回的数据
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     *
     [{"id":1,"name":"北京"},{"id":2,"name":"上海"},
     {"id":3,"name":"天津"},{"id":4,"name":"重庆"},
     {"id":5,"name":"香港"},{"id":6,"name":"澳门"},
     {"id":7,"name":"台湾"},{"id":8,"name":"黑龙江"},
     {"id":9,"name":"吉林"},{"id":10,"name":"辽宁"},
     {"id":11,"name":"内蒙古"},{"id":12,"name":"河北"},
     {"id":13,"name":"河南"},{"id":14,"name":"山西"},
     {"id":15,"name":"山东"},{"id":16,"name":"江苏"},{"id":17,"name":"浙江"},
     {"id":18,"name":"福建"},{"id":19,"name":"江西"},{"id":20,"name":"安徽"},
     {"id":21,"name":"湖北"},{"id":22,"name":"湖南"},{"id":23,"name":"广东"},
     {"id":24,"name":"广西"},{"id":25,"name":"海南"},{"id":26,"name":"贵州"},{
     "id":27,"name":"云南"},{"id":28,"name":"四川"},{"id":29,"name":"西藏"},{"id":30,"name":"陕西"},
     {"id":31,"name":"宁夏"},{"id":32,"name":"甘肃"},{"id":33,"name":"青海"},{"id":34,"name":"新疆"}]
     http://guolin.tech/api/china 34个省的数据就是一个jsonArray里面包含着34个jsonObject对象
     */

    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvince =new JSONArray(response);
                for (int i = 0; i < allProvince.length(); i++) {
                    JSONObject provinceObject=allProvince.getJSONObject(i);
                    Province province= new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     *以云南为例
     * [{"id":251,"name":"昆明"},{"id":252,"name":"大理"},{"id":253,"name":"红河"},{"id":254,"name":"曲靖"},{"id":255,"name":"保山"},{"id":256,"name":"文山"},{"id":257,"name":"玉溪"},
     * {"id":258,"name":"楚雄"},{"id":259,"name":"普洱"},{"id":260,"name":"昭通"},{"id":261,"name":"临沧"},{"id":262,"name":"怒江"},{"id":263,"name":"香格里拉"},{"id":264,"name":"丽江"},{"id":265,"name":"德宏"},{"id":266,"name":"景洪"}]
     *
     * @param response 服务器返回的city数据（http://guolin.tech/api/china/27）云南为例，里面有26个城市
     * @param provinceId 记录的是云南的Id
     * @return false 就是没成功
     */
    public static  boolean handleCityResponse(String response ,int provinceId){
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities= new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject citeObject= allCities.getJSONObject(i);
                    City  city= new City();
                    city.setCityName(citeObject.getString("name"));
                    city.setCityCode(citeObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    /**
     *
     * @param response 返回的city Json数据
     * @param cityId 记录上级城市的id
     * @return false失败 true 成功
     */
    public static  boolean handleCountyResponse(String response,int cityId){

        try {
            JSONArray allCounties=new JSONArray(response);
            for (int i = 0; i < allCounties.length(); i++) {
                JSONObject countyObject =allCounties.getJSONObject(i);
                County county=new County();
                county.setCountyName(countyObject.getString("name"));
                county.setWeatherId(countyObject.getString("weather_id"));
                county.setCityId(cityId);
                county.save();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将返回的json数据解析成weather实体类
     * @param response 返回的json数据
     * @return 解析成weather类
     */
    public static Weather handleWeatherResponse(String response){

        try {
            JSONObject jsonObject= new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
