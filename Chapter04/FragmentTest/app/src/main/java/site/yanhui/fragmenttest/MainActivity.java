package site.yanhui.fragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import site.yanhui.fragment.AnotherRightFragment;
import site.yanhui.fragment.RightFragment;

/**
 * 1.创建待添加的碎片实例
 * 2.获取FragmentManager，在活动中可以直接调用getSupportFragmentManager()方法得到
 * 3.开启一个事物，通过调用beginTransaction()方法开启
 * 4.向容器里面添加或者替换掉碎片，一般使用replace方法，需要传入容器的id和待添加的碎片的实例
 * 5.提交事物，调用commit()方法。
 */
public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(this);//绑定监听事件

        replaceFragment(new RightFragment());// right_layout 替换成rightFragment
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                //替换fragment
                replaceFragment(new AnotherRightFragment());

                break;

            default:
                break;
        }
    }

    //写一个替换fragment的方法
    private void  replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager(); //单例模式写成
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.right_layout,fragment);//替换掉right_layout 换上传入的fragment
       transaction.addToBackStack(null);//fragment添加到返回栈中
        transaction.commit();


    }

}
