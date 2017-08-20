package site.yanhui.uiwidgetest;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 绑定button的三种方法。
 * 1. 匿名的方式
 * 2.实现接口
 * 3.activity_main中实现OnClick属性
 */

/*public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //为了节省内存，使用使用后随机释放内存的做法，初始化button
        //并注册一个监听器，采用匿名类的方式
        Button btn= (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //此处田间逻辑
            }
        });


    }
}*/

/*public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //为了节省内存，使用使用后随机释放内存的做法，初始化button
        //并注册一个监听器
        Button btn= (Button) findViewById(R.id.button);
        //实现接口的方式，绑定button点击事件
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Toast.makeText(this, TAG, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}*/

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private EditText editText;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        mImageView = (ImageView) findViewById(R.id.image_view);
        editText = (EditText) findViewById(R.id.edit_text);
        editText.setOnClickListener(this);
        button.setOnClickListener(this);
        mProgressBar = (ProgressBar) findViewById(R.id.ProgressBar);

    }

    //重写xml里面的onClick方法
//    public void Click(View view) {
////        Toast.makeText(this, TAG, Toast.LENGTH_SHORT).show();
//        mImageView.setImageResource(R.drawable.avatar);
//    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_text:  //为了editText添加点击事件
                String s = editText.getText().toString();
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button:
                //动态设置imageView的图片资源
//                mImageView.setImageResource(R.drawable.avatar);

                /*
                1.所有android控件都实现了三个属性
                visible(可见的，占位置大小)，invisible（不可见，但是占位置大小）
                gone（不仅不可见，而且不占位置，透明的）
                2. 默认是visible的。
                 */
//                if (mProgressBar.getVisibility()==View.GONE){
//                    mProgressBar.setVisibility(View.VISIBLE);
//                }else{
//                    mProgressBar.setVisibility(View.GONE);
//                }
//                break;

                //设置progress的进度条
//                int progress = mProgressBar.getProgress();
//                 progress+=10;
//                mProgressBar.setProgress(progress);

//                初始化dialog,配置dialog
                AlertDialog.Builder dialog= new  AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("FirstDialog");//设置标题
                dialog.setMessage("确定要删除数据吗？"); //设置内容
                dialog.setCancelable(false);//如果设置成flase，那么将不能通过back键取消
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();

//                ProgressDialog progessDialog= new ProgressDialog(MainActivity.this);
//                progessDialog.setTitle("ProgressDialog");
//                progessDialog.setMessage("Loading");
//                progessDialog.setCancelable(true);//如果设置成flase，那么将不能通过back键取消
//                progessDialog.show();

            default:
                break;

        }
    }
}
