package site.yanhui.uibestpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import site.yanhui.adapter.MsgAdapter;
import site.yanhui.bean.Msg;

public class MainActivity extends AppCompatActivity {

    //初始化一个集合来存放msg对象
    private List<Msg> msgList= new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView mRecyclerView;
    private MsgAdapter msgAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();

        //初始化组件
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);

        mRecyclerView = (RecyclerView) findViewById(R.id.msg_recycle_view);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);//设置为linearlayout布局
        //传入数据，进行适配
        msgAdapter = new MsgAdapter(msgList);
        mRecyclerView.setAdapter(msgAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content= inputText.getText().toString();
                if (!"".equals(content)){
                    Msg msg= new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    msgAdapter.notifyItemInserted(msgList.size()-1);//当有新消息时，刷新listview显示
                    mRecyclerView.scrollToPosition(msgList.size()-1);
                    inputText.setText("");//清空内容
                }
            }
        });


    }

    private void initMsg() {

        Msg msg1= new Msg("hello guy !",Msg.TYPE_RECEIVED);
         msgList.add(msg1);
        Msg msg2 =new Msg("hello  who is that ?",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3  = new Msg("this is Archer. nice to meet U ",Msg.TYPE_RECEIVED);
        msgList.add(msg3);

    }


}
