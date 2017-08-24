package site.yanhui.sharedpreferencestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


/**
 * 1.sharedpreferences的使用
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveData = (Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //必须先要得到edit编辑的权限，然后才能写入数据
                SharedPreferences.Editor editor = getSharedPreferences("data.xml", MODE_PRIVATE).edit();
                editor.putString("name", "Archer");//压入键值对
                editor.putBoolean("isMarried", false);
                editor.putInt("age", 24);
                editor.putFloat("salary", 0f);
                editor.apply();//必须提才会生效
            }
        });


        Button RestoreData= (Button) findViewById(R.id.restore_data);
        RestoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref=getSharedPreferences("data.xml",MODE_PRIVATE);
                String name = pref.getString("name", "");//传入两个参数，一个是键，另一个是不存的默认值
                int age = pref.getInt("age", 1);
                float salary = pref.getFloat("salary", 11f);
                boolean ismarried = pref.getBoolean("ismarried", true);//该key不存在，默认返回true
                boolean isMarried = pref.getBoolean("isMarried", false);
                Log.d(TAG, name);
                Log.d(TAG,age+"" );
                Log.d(TAG, salary+"");
                Log.d(TAG, ismarried+"");
                Log.d(TAG, isMarried+"");
            }
        });
    }
}
