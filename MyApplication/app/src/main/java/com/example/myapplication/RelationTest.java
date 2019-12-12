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

        String result=getIntent().getStringExtra("result");

        //웹뷰 띄워주는 페이지
        WebView mWebView = (WebView)findViewById(R.id.webview);

        WebSettings set = mWebView.getSettings();
        set.setJavaScriptEnabled(true);
        set.setDefaultTextEncodingName("utf-8");

        mWebView.loadUrl("file:///android_asset/www/index.html?text="+result);

    }

    @Override
    public void onBackPressed() {


//        Intent intent = getIntent();
//
//        String filename = intent.getStringExtra("filename");
//        int page = Integer.parseInt(intent.getStringExtra("page"))+1;
//        String title = intent.getStringExtra("title");
//        String allpage = intent.getStringExtra("allpage");
//        String userid = intent.getStringExtra("userid");
//
//        intent = new Intent(getApplicationContext(),myBook.class);
//
//        intent.putExtra("filename",filename);
//        intent.putExtra("page",page);
//        intent.putExtra("title",title);
//        intent.putExtra("allpage",allpage);
//        intent.putExtra("userid",userid);
//
//        startActivity(intent);
        super.onBackPressed();
    }








}
