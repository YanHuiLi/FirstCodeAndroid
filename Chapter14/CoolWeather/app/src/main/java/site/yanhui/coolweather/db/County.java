package site.yanhui.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Archer on 2017/9/11.
 * <p>
 * 功能描述：
 * 记录县级的城市信息
 */

public class County extends DataSupport {
    private  int  id;
    private String countyName; //县的名字
    private  String weatherId;  //记录县对应的天气的id
    private int cityId;  //记录当前县的所属城市的id值

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
