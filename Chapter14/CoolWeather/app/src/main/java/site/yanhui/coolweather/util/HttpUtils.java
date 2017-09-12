package site.yanhui.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Archer on 2017/9/11.
 * <p>
 * 功能描述：
 * 因为是要和服务器有数据传输，封装一个httpUtils工具类
 * 用于获取数据
 */

public class HttpUtils {
    public static void  sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient okHttpClient =new OkHttpClient();
        Request request =new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
