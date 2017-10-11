package site.yanhui.servicetest.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import site.yanhui.servicetest.MainActivity;
import site.yanhui.servicetest.R;

/**
 * create at 2017/9/3 by 10:59
 *
 * @author Archer
 * @function
 */

public class MyService extends Service {


    private static final String TAG = "MyService";

    //构造
    public MyService() {
    }


    private DownLoadBinder mBinder = new DownLoadBinder();

    /**
     * 模拟一个类来完成下载的功能
     */
    public class DownLoadBinder extends Binder {

        //开始下载
        public void startBinder() {
            Log.d(TAG, "startBinder: executed");
        }

        //得到进度
        public int getProgress() {
            Log.d(TAG, "getProgress: executed");
            return 0;
        }

    }

    /**
     * @param intent 传入一个intent对象
     * @return 返回一个IBinder类型的数据
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new android.support.v4.app.NotificationCompat.Builder(this)
                .setContentText("这是一个前台服务")
                .setContentTitle("前台服务")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        startForeground(1, notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: "); //放在return之前
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
