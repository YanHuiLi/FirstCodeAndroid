package site.yanhui.broadcastbestpractice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.yanhui.broadcastbestpractice.R;

public class MainActivity extends BaseActivity {


    @BindView(R.id.ForceOnline_button)
    Button ForceOnlineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //发送一条广播，实现强制下线
        ForceOnlineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //必须是定义一个全局的广播，这样的话，一旦你的账号被非法操作了，你只要发起一个全局广播
                //就可以实现实现挤下线的功能
                sendBroadcast(new Intent("site.yanhui.broadcastbestpractice.FORCE_OFFLINE"));
            }
        });
    }
}
