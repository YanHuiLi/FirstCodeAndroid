package site.yanhui.servicebestpractice.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.io.File;

import site.yanhui.servicebestpractice.MainActivity;
import site.yanhui.servicebestpractice.R;
import site.yanhui.servicebestpractice.listener.DownloadListener;
import site.yanhui.servicebestpractice.task.DownloadTask;

/**
 * Created by Archer on 2017/9/4.
 * <p>
 * 功能描述：
 * 1.为了保证DownloadTask能在后台一直执行，所以需要一个service。
 */

public class DownLoadService extends Service {

    private DownloadTask downloadTask;
    private String downloadUrl;


    private DownloadListener listener = new DownloadListener() {
        /**
         *
         * @param progress 在前台服务显示进度
         */
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading....", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Downloading success", -1));
            Toast.makeText(DownLoadService.this, "Downloading success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPause() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Downloading Pause", -1));
            Toast.makeText(DownLoadService.this, "Downloading Pause", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Downloading Canceled", -1));
            Toast.makeText(DownLoadService.this, "Downloading Canceled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Downloading Failed", -1));
            Toast.makeText(DownLoadService.this, "Downloading Failed", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * @param title    notificaton的标题
     * @param progress notification的进度
     * @return 返回一个notification
     */
    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);//使用NotificationCompat.Builder模式创建notification实例
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        //把pendingIntent加入进去
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle(title);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

    /**
     * @return 得到NotificationManager的实例
     */
    private NotificationManager getNotificationManager() {

        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }


    DownloadBinder mBinder= new DownloadBinder();

    /**
     * Return the communication channel to the service.  May return null if
     * clients can not bind to the service.  The returned
     * {@link IBinder} is usually for a complex interface
     * that has been <a href="{@docRoot}guide/components/aidl.html">described using
     * aidl</a>.
     * <p>
     * <p><em>Note that unlike other application components, calls on to the
     * IBinder interface returned here may not happen on the main thread
     * of the process</em>.  More information about the main thread can be found in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html">Processes and
     * Threads</a>.</p>
     *
     * @param intent The Intent that was used to bind to this service,
     *               as given to {@link Context#bindService
     *               Context.bindService}.  Note that any extras that were included with
     *               the Intent at that point will <em>not</em> be seen here.
     * @return Return an IBinder through which clients can call on to the
     * service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class DownloadBinder extends Binder {
        /**
         *
         * @param url 传入一个下载的地址
         */
        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(1, getNotification("Downloading...", 0));
                Toast.makeText(DownLoadService.this, "Downloading...", Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload(){
            if (downloadTask!=null) {
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload(){
            if (downloadTask!=null) {
                downloadTask.cancelDownload();
            }else {
                if (downloadUrl!=null) {
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory+fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownLoadService.this, "Canceled", Toast.LENGTH_SHORT).show();
                }
            }
        }


    }
}
