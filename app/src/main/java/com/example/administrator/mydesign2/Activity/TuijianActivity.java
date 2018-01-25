package com.example.administrator.mydesign2.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mydesign2.AsyncTask.Differtask;
import com.example.administrator.mydesign2.AsyncTask.MessageSend;
import com.example.administrator.mydesign2.R;
import com.example.administrator.mydesign2.User;
import com.google.gson.Gson;

public class TuijianActivity extends AppCompatActivity implements MessageSend{
    private TextView tv;
    private ImageView iv;
    private WebView webView;
    private String url;
    private Gson gson  = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuijian);


//        webView = (WebView) findViewById(R.id.tv1);

//        webView.loadUrl("http://www.baidu.com/");
////设置不用系统浏览器打开,直接显示在当前Webview
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });


    }
 /*异步任务 请求参数*/
    private void Update(){
        try{
            url = "http://192.168.4.162:8080/canteen/list";
            User user = new User();
            user.setUsername("admin");
            user.setPassword("111111");
            String value = gson.toJson(user);
            Differtask updatetext = new Differtask(this, url, value ,this);
            updatetext.execute();
        }catch(Exception e){

        }
    }

    @Override
    public void receivedMsg(String result) {
        tv.setText(result);
    }
}
