package site.yanhui.androidthreadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 1.该案例是为了说明，android系统允许在主线程中更新UI，但是在子线程中却不允许
 * 2.但是有时候，我们必须经过子线程得到的数据而进行更新UI
 * 3.因此使用到handler提供了完全的解决方法。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int UPDATE_TEXT = 1;
    private TextView textView;

    //使用handler来更新数据
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    textView.setText("nice to meet U  ");
                    break;
                default:

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// 原布局文件是 hello world
        InitUI();
        textView.setText("你好");//验证主线程中可以更新

    }

    private void InitUI() {
        Button ChangeText = (Button) findViewById(R.id.change_text);
        ChangeText.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change_text:

                //耗时操作的话，新开一个子线程来操作。不然会造成主线程阻塞ANR异常
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message(); //实例化一个massage对象
                        message.what = UPDATE_TEXT; //携带一个int值
                        handler.sendMessage(message);//发送这个message
                    }
                }).start();

                break;

            default:
                break;

                /*
                android.view.ViewRootImpl$CalledFromWrongThreadException:
                Only the original thread that created a view hierarchy can touch its views.

                证明了android只允许在主线程里面更新ui而不允许在子线程里面更新。

                 new Thread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("nice to meet u ");
                    }
                }).start();
                 */


        }

    }
}
