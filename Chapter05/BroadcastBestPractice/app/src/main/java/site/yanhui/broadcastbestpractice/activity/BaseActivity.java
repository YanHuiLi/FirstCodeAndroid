package site.yanhui.broadcastbestpractice.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import site.yanhui.broadcastbestpractice.utils.ActivityCollector;

/**
 * 抽取一个基类，利用工具类，实现对Activity的统一管理
 * Created by Archer on 2017/8/23.
 */

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver offlineReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        intentFilter = new IntentFilter();
        intentFilter.addAction("site.yanhui.broadcastbestpractice.FORCE_OFFLINE");
        offlineReceiver = new ForceOfflineReceiver();
        registerReceiver(offlineReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (offlineReceiver!=null){
            unregisterReceiver(offlineReceiver);
            offlineReceiver=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

     class ForceOfflineReceiver  extends BroadcastReceiver{
         @Override
         public void onReceive( final  Context context, Intent intent) {

            //弹出一个对话框
             AlertDialog.Builder builder= new  AlertDialog.Builder(context);//得到 AlertDialog的实例
             builder.setTitle("警告");
             builder.setMessage("你已经被强制下线了,请重新登陆.");
             builder.setCancelable(false);//不允许back退回
             builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     ActivityCollector.finishAll();
                     //注意这个写法，用context来写的
                     Intent intent =new Intent(context,LoginActivity.class);
                     context.startActivity(intent);

                 }
             });
             builder.show();

         }
     }
}
