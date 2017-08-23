package site.yanhui.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Broadcast 的使用
 * 动态注册监听网络变化
 * 1.注意registerReceiver（）方法的构造函数。这是最关键的一点。
 * 2.需要一个intentFilter 和 Receiver
 * 3.IntentFilter 这个意图过滤器的作用就是确定是哪条Action传入，这里传入的
 *   android.net.conn.CONNECTIVITY_CHANGE 是系统默认的，通过intentFilter传入此action才能让系统知道，
 *   需要监听的是网络的变化。
 *4.receiver 接收器的意思，其实是我们之前传入了某个action以后，系统给我们的回调函数，直白的说就是
 *  当这个action得到执行的时候，就会执行此回调函数，用来执行一些操作。
 *5.实现receiver的方式，就是通过继承BroadcastReceiver，实现回调函数onReceiver，执行操作。
 *
 *Broadcast广播接收器，本质上来说就是希望程序员在给intentFiler添加一个action的时候（可以等于发送一条广播）的时候，
 * 回调的一个OnReceiver方法，执行相应的操作，在这个方法里面，不要进行一些过多的逻辑和任何耗时操作，因为OnReceiver方法中不允许
 * 开启线程。
 *
 * 一般用于创建一条状态栏或者启动一个服务。
 *
 *
 * 在使用本地广播的时候，一定要记住，原理就是 得到一个经过单例模式得到的LocalBroadcastManager，进行管理
 * 因此在 注册 和 取消注册的时候，都必须使用的LocalBroadcastManager 进行管理。
 *
 * 这点区别与 全局广播，就是本地广播的精髓所在。
 *
 */
public class MainActivity extends AppCompatActivity {



    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //动态注册广播
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChangeReceiver = new NetworkChangeReceiver();
//        registerReceiver(networkChangeReceiver,intentFilter);


        //使用本地广播
        //获取实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送一条广播让 MyBroadcastReceiver接收
//                Intent intent =new Intent("site.yanhui.broadcasttest.MY_BROADCAST");
//                sendBroadcast(intent);//发送一条标准广播，只要是添加了对应的action的所有app都能收到
//                  sendOrderedBroadcast(intent,null); //第二个参数和权限有关的string，传入null即可。

                //动态注册本地广播
               Intent intent =new Intent("site.yanhui.broadcasttest.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);


            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("site.yanhui.broadcasttest.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        //本地注册
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    //动态注册广播，一定要在onDestroy方法里面实现 unregisterReceiver方法取消注册。
    //暂时不知道原因
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(networkChangeReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    /*
    public class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

            //通过 getSystemService传入封装好的数据，就可以得到connectivityManager，用于管理
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

           //通过connectivityManager的getActiveNetworkInfo()方法，就可以得到一个networkInfo
            //里面存有一些当前网络信息状态和数值。
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            //如果网络情况不为空或者是网络情况是可以用的。输出toast
            //注意这里由于调用了，系统的一些敏感权限
            //需要在manifests添加权限
            //<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
            if (networkInfo!=null&& networkInfo.isAvailable()){
            Toast.makeText(context, "network is Available", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "NetWork is unAvailable", Toast.LENGTH_SHORT).show();
            }

        }
    }
*/
    public class LocalReceiver  extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "Receiver the local Broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}
