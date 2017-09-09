package site.yanhui.materialtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import site.yanhui.materialtest.R;

/**
*create at 2017/9/9 by 16:40
*作者：Archer
*功能描述：
 * 新建一个FruitActivity来作为点击水果以后的详情展示页面
*/
public class FruitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
    }
}
