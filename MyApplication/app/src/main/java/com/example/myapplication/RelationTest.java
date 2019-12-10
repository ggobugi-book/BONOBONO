package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class RelationTest extends AppCompatActivity {

    private final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation_test);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String page = intent.getStringExtra("page");
        String result="";

        try{

            RegisterActivity task = new RegisterActivity();
            //result = task.execute("relation",title, page).get();

            //Log.d("ggb :" ,result);

        }
        catch(Exception e){

        }







        //웹뷰 띄워주는 페이지
        WebView mWebView = (WebView)findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/www/index.html");
        //mWebView.loadUrl("F:/Github/ggbook/MyApplication/app/src/main/assets/www/index.html");



    }








}
