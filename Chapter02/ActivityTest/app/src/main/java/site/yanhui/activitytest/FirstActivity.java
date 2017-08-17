package site.yanhui.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.R.id.button1;

public class FirstActivity extends BaseActivity {

    private static final String TAG = "FirstActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d(TAG, "task id is "+getTaskId());
        setContentView(R.layout.first_layout);
        Button button= (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent= new Intent(FirstActivity.this,SecondActivity.class);
//                startActivity(intent);

                //传递两个特别重要的参数过去
                SecondActivity.actionStart(FirstActivity.this,"data1","data2");
            }
        });

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
}
