package site.yanhui.layout;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import site.yanhui.uicustomviews.R;

/**
 * Created by Archer on 2017/8/19.
 * 1.自定义控件TitleLayout继承LinearLayout
 * 2.通过LayoutInflater的from方法得到LayoutInflater的对象
 * 3.调用inflate方法加载之前设计好的title
 */

public class TitleLayout  extends LinearLayout{
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
//        Button mButtonBack= findViewById(R.id.title_back);
        Button  mButtonEdit =findViewById(R.id.title_edit);
        Button mButtonBack = findViewById(R.id.title_back);
        mButtonBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //结束当前运用
                ((Activity)getContext()).finish();
            }
        });
        mButtonEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "you click the edit button", Toast.LENGTH_SHORT).show();
            }
        });

    }



}
