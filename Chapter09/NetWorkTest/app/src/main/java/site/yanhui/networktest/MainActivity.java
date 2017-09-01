package site.yanhui.networktest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitUI();

    }

    private void InitUI() {
        Button sendRequest= (Button) findViewById(R.id.Send_request);
        sendRequest.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.response_text);
        Button sendRequestOkhttp= (Button) findViewById(R.id.send_request_okhttp);
        sendRequestOkhttp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Send_request:
                sendRequestWithHttpURLConnection();
                break;
            case R.id.send_request_okhttp:
                sendRequestWithOkHttp();
                break;
        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                //实例化一个okHttpClient
                OkHttpClient okHttpClient= new OkHttpClient();
                //request发起一个请求
                Request request= new Request.Builder()
                        .url("http://www.google.com")
                      .build();
                    Response response = okHttpClient.newCall(request).execute();
                    String string = response.body().string();
                    showResponse(string);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void sendRequestWithHttpURLConnection() {
        //新开启一个线程来访问网络
         new Thread(new Runnable() {

             HttpURLConnection connection=null;
             BufferedReader bufferedReader=null;

             @Override
             public void run() {
                 try {
                     URL url = new URL("http://www.google.com");//实例化一个URL
                     connection = (HttpURLConnection) url.openConnection();//打开这个链接
                     connection.setRequestMethod("GET");//得到数据
                     connection.setConnectTimeout(8000);
                     connection.setReadTimeout(8000);
                     InputStream inputStream = connection.getInputStream();//得到获取的输入流

                     //下面对输入流进行读取
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                     //用一个容器来装
                     StringBuilder response=new StringBuilder();

                     String line;
                     while ((line = bufferedReader.readLine()) != null) {
                         response.append(line);//把line加入到response里面就得到数据
                     }
                     showResponse(response.toString());//展现在ui中

                 } catch (Exception e) {
                     e.printStackTrace();
                 }finally {
                     //不要忘记释放资源
                     if (bufferedReader!=null) {
                         try {
                             bufferedReader.close();
                         } catch (IOException e) {
                             e.printStackTrace();
                         }

                         if (connection!=null) {
                             connection.disconnect();
                         }
                     }
                 }
             }
         }).start();
    }

    private void showResponse(final String reponseTest) {
        /*
         * Runs the specified action on the UI thread. If the current thread is the UI
         * thread, then the action is executed immediately. If the current thread is
         * not the UI thread, the action is posted to the event queue of the UI thread.
         *
         * @param action the action to run on the UI thread
         */
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里更新Ui
                textView.setText(reponseTest);
            }
        });
    }
}
