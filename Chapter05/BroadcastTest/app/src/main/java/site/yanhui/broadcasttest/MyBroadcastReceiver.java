package site.yanhui.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**i
 * 1.设计一个自己的广播接收器
 * 2.静态注册，添加一条action在Manfests文件里面
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "receive in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
//       abortBroadcast();//标准广播被拦截了。
    }
}
