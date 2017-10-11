package site.yanhui.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import site.yanhui.servicetest.service.MyIntentService;
import site.yanhui.servicetest.service.MyService;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MainActivity";
    //拿到DownBinder的实例开启下载功能
    private MyService.DownLoadBinder downLoadBiner;

    //用serviceConnection和服务通讯
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downLoadBiner = (MyService.DownLoadBinder) service;
            downLoadBiner.startBinder();
            downLoadBiner.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    /**
     * 1.初始化组件
     * 2。绑定监听
     */
    private void initUI() {
        Button StartService = (Button) findViewById(R.id.start_service);
        Button StopService = (Button) findViewById(R.id.stop_service);
        StartService.setOnClickListener(this);
        StopService.setOnClickListener(this);

        Button bindService = (Button) findViewById(R.id.Bind_service);
        Button UnbindService = (Button) findViewById(R.id.unbind_service);
        bindService.setOnClickListener(this);
        UnbindService.setOnClickListener(this);

        Button StartIntentService= (Button) findViewById(R.id.start_intent_service);
         StartIntentService.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent StartServiceIntent = new Intent(this, MyService.class);
                startService(StartServiceIntent);
                break;

            case R.id.stop_service:
                Intent StopServiceIntent = new Intent(this, MyService.class);
                stopService(StopServiceIntent);
                break;

            case R.id.Bind_service:

                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent,serviceConnection,BIND_AUTO_CREATE);//绑定服务只有onCreate方法执行

                break;
            case R.id.unbind_service:
                unbindService(serviceConnection);//解绑服务
                break;

            case R.id.start_intent_service:
                Log.d(TAG, "the Main process Id is "+ Thread.currentThread().getId());
                Intent intent = new Intent(this, MyIntentService.class);
                startService(intent);
                break;
            default:
                break;

        }
    }
}
