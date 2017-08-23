package site.yanhui.broadcastbestpractice.utils;

/**
 * 工具类。
 * Created by Archer on 2017/8/23.
 */


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于管理所有的活动
 */
public class ActivityCollector {

    //新建一个list用于存放activity
    public  static List<Activity> activities= new ArrayList<>();


    //添加activity
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    //移除activity
    public static void  removeActivity(Activity activity){
        activities.remove(activity);
    }

    //移除所有的activity
    public  static  void finishAll(){
        for (Activity activity : activities) {
            if (activity!=null){
                activity.finish();
            }
        }
    }

}
