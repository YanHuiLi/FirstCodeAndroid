package site.yanhui.activitylifecycletest;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

      if(savedInstanceState!=null){
          String tempData= savedInstanceState.getString("data_key");
          Log.d(TAG, tempData);

      }



        //这样写的目的因为不用拿到button的局部变量
        Button StartNormalActivity = (Button) findViewById(R.id.button_Normal);
        Button StartDialogActivity = (Button) findViewById(R.id.button_dialog);
        
        StartDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent= new Intent(MainActivity.this,DialogActivity.class);
                startActivity(intent);//别忘记了startActivity


       }
        });
        
        StartNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent= new Intent(MainActivity.this,NormalActivity.class);
                startActivity(intent);
            }
        });
        
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //采用bundle来存取数据
        String tempData= "something you just typed";
        outState.putString("data_key",tempData);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart:");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
}
