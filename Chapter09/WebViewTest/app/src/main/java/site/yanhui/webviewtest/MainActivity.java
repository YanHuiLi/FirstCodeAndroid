package site.yanhui.webviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);//设置javascript脚本为可用
        webView.setWebViewClient(new WebViewClient());//在webview中打开，而不是去用默认浏览器
        webView.loadUrl("http://www.google.com");//一定要注意http头
    }
}
