package site.yanhui.activitytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.R.attr.data;

public class SecondActivity extends BaseActivity {

    private static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        Log.d(TAG, "task id is "+getTaskId());
        Button button = (Button) findViewById(R.id.Back_First);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent =new Intent(SecondActivity.this,ThirdActivity.class);
//                startActivity(intent);

                //接收过来的数据
                Intent intent=getIntent();
                String param1 = intent.getStringExtra("param1");
                String param2 = intent.getStringExtra("param2");
                Log.d(TAG, param1);
                Log.d(TAG, param2);



            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public static void actionStart(Context context,String data1,String data2){

        Intent intent =new Intent(context,SecondActivity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);

        context.startActivity(intent);

    }
}
