package site.yanhui.activitytest;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Archer on 2017/8/17.
 */

@SuppressWarnings("ALL")
public class ActivityCollector {

    public static List<Activity> activities= new ArrayList<>();

    public static void AddActivity(Activity activity){
        activities.add(activity);

    }

    public  static void removeActivity(Activity activity){

        activities.remove(activity);
    }

    public static  void  finishAll(){
        for (Activity activity : activities) {
            if (!activity.isFinishing()){

                activity.finish();
            }

        }
    }
}

