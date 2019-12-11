package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

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

        //웹뷰 띄워주는 페이지
        WebView mWebView = (WebView)findViewById(R.id.webview);

        WebSettings set = mWebView.getSettings();
        set.setJavaScriptEnabled(true);
        set.setDefaultTextEncodingName("utf-8");

        mWebView.loadUrl("file:///android_asset/www/index.html?text="+getIntent().getStringExtra("result"));

    }








}
