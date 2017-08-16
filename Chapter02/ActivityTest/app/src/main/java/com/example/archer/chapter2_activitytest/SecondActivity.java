package com.example.archer.chapter2_activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//拿到firstActivity传递过来的数据
//        Intent intent=getIntent();
//        String extra_data = intent.getStringExtra("extra_data");
//        Log.d("SecondActivity", extra_data);


        //返回数据给firstActivity
        Button button= (Button) findViewById(R.id.Button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.putExtra("data_return","hello Activity");// 把数据传给intent，返回去
                setResult(RESULT_OK,intent);//设置状态码和intent
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent();
        intent.putExtra("data_return","hello Activity");// 把数据传给intent，返回去
        setResult(RESULT_OK,intent);//设置状态码和intent
        finish();
    }
}
