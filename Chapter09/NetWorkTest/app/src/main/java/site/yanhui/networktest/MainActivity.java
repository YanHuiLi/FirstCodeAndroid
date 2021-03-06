package site.yanhui.networktest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import site.yanhui.networktest.bean.AppBean;


/**
*create at 2017/9/10 by 19:51
*作者：Archer
*功能描述：
 * 1.使用pull和sax的方式解析xml数据
 * 2.使用jsonObject处理json数据
 * 3.使用Gson处理json数据
 * 4.使用第三方库okhttp发送请求
*/
@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MainActivity";
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
       Button JsonButton= (Button) findViewById(R.id.JsonButton);
        JsonButton.setOnClickListener(this);
        Button GsonButton= (Button) findViewById(R.id.GsonButton);
       GsonButton.setOnClickListener(this);
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
            case R.id.JsonButton:
                sendRequestWithOkHttpAndJson();
                break;
            case R.id.GsonButton:
                parseGson();

                break;
        }
    }

    /**
     * 使用Gson解析json
     */
    private void parseGson() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //实例化一个okHttpClient
                    OkHttpClient okHttpClient= new OkHttpClient();
                    //request发起一个请求
                    Request request= new Request.Builder()
//                        .url("http://yanhui.site")
                            .url("http://ogtmd8elu.bkt.clouddn.com/aaa.json")
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData); //界面显示返回的数据
                    parseJsonWithGson(responseData);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 使用gson解析数据
     * @param data
     */
    private void parseJsonWithGson(String data) {
        Gson gson= new Gson();
        List<AppBean> appList= gson.fromJson(data,new TypeToken<List<AppBean>>(){}.getType());
        for (AppBean app : appList) {
            Log.d(TAG, "parseJsonWithGson:  id is " + app.getId());
            Log.d(TAG, "parseJsonWithGson: name is " + app.getName());
            Log.d(TAG, "parseJsonWithGson: version is " + app.getVersion());

        }
    }

    /**
     * 使用okhttp发送请求
     * 并且使用jsonObject的方式解析数据
     */
    private void sendRequestWithOkHttpAndJson() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    //实例化一个okHttpClient
                    OkHttpClient okHttpClient= new OkHttpClient();
                    //request发起一个请求
                    Request request= new Request.Builder()
//                        .url("http://yanhui.site")
                            .url("http://ogtmd8elu.bkt.clouddn.com/aaa.json")
                            .build();
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData); //界面显示返回的数据
                    parseJsonWithJsonObject(responseData);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 使用jsonObject处理返回的json数据
     * @param data
     *
     * 打断点，看代码的执行顺序
     * {}一对花括号代表一个jsonObject
     * []代表一个jsonArray 里面包含多个jsonObject
     */
    private void parseJsonWithJsonObject(String data) {
        try {
            JSONArray jsonArray=new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject= jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d(TAG, "parseJsonWithJsonObject: id is  "+id);
                Log.d(TAG, "parseJsonWithJsonObject: name is "+name);
                Log.d(TAG, "parseJsonWithJsonObject: version is " +version );

            }
        } catch (JSONException e) {
            e.printStackTrace();
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
//                        .url("http://yanhui.site")
                        .url("http://ogtmd8elu.bkt.clouddn.com/aaa.xml")
                      .build();
                    Response response = okHttpClient.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData); //界面显示返回的数据
                    parseXMLWithPull(responseData);  //Pull方法解析XML
                    parseXMLWithSax(responseData); //sax的方式解析XML
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 解析从服务器返回来的xml数据
     * @param data 返回的string 数据
     */
    private void parseXMLWithSax(String data) {
        SAXParserFactory factory =SAXParserFactory.newInstance();
        try {
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler contentHandler= new ContentHandler();
            //hadler实例放入在xmlReader中
            xmlReader.setContentHandler(contentHandler);
            //开始执行
            xmlReader.parse(new InputSource(new StringReader(data)));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 采取Pull方式进行解析
     * @param data 和服务器交互，返回得到的XML data数据
     */
    private void  parseXMLWithPull(String data) {
        try {
            //得到XmlPullParserFactory的实例
            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser mXmlpullParser=factory.newPullParser();//得到解析器
            mXmlpullParser.setInput(new StringReader(data));
            int eventType = mXmlpullParser.getEventType();
            String id="";
            String name="";
            String Version="";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = mXmlpullParser.getName();
                switch (eventType) {
                    //开始解析某个节点
                    /**
                     *
                     <apps>
                      <app>
                     <id>1</id>
                     <name>Google Maps</name>
                     <Version>1.0</Version>
                     </app>

                     <app>
                     <id>2</id>
                     <name>chrome</name>
                     <Version>2.1</Version>
                     </app>

                     <app>
                     <id>3</id>
                     <name>Google Play</name>
                     <Version>2.3</Version>
                     </app>

                     </apps>

                     我在服务器上存放的数据如上，进入到case: START_TAG语句的时候，会先检查
                     apps不匹配就跳出，再检查app的，还是不匹配就跳出，一直带检查到id的匹配上了，就赋值
                     然后name version，接下来执行endtag   /id /name /version /app检测到了就输出之前存入的数据
                     然后调用了next方法继续检测下一段数据。
                     打个断点就清楚到底是怎么执行的了。
                     */

                    case XmlPullParser.START_TAG:{
                        if ("id".equals(nodeName)) {
                            id=mXmlpullParser.nextText();
                        }else if ("name".equals(nodeName)){
                            name=mXmlpullParser.nextText();
                        }else if ("Version".equals(nodeName)){
                            Version=mXmlpullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG:{
                        if ("app".equals(nodeName)){
                            Log.d(TAG, "parseXMLWithPull: id is "+id);
                            Log.d(TAG, "parseXMLWithPull: name is  "+name);
                            Log.d(TAG, "parseXMLWithPull: version is "+ Version);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType=mXmlpullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRequestWithHttpURLConnection() {
        //新开启一个线程来访问网络
         new Thread(new Runnable() {

             HttpURLConnection connection=null;
             BufferedReader bufferedReader=null;

             @Override
             public void run() {
                 try {
                     URL url = new URL("http://www.yanhui.site");//实例化一个URL
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
