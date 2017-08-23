package site.yanhui.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


//manifests里面静态注册的BootCompleteReceiver
//接受的是<action android:name="android.intent.action.BOOT_COMPLETED"/>
//实现开机启动，注意需要<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/> 权限。

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_SHORT).show();
    }
}
