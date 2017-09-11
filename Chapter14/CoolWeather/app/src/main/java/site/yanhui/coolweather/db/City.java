package site.yanhui.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Archer on 2017/9/11.
 * <p>
 * 功能描述：
 * 新建一个city类来存储city的数据
 */

public class City extends DataSupport {
     private int id;
    private String cityName; //城市的名字
    private String cityCode;  //城市的代号
    private int provinceId; //记录当前市所属城市的id值

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
