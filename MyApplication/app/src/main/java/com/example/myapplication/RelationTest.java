package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.service.autofill.Sanitizer;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class RelationTest extends AppCompatActivity {

    getRelation gr = new getRelation();
    Thread t = new Thread(gr);

    private final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation_test);

        Intent intent = getIntent();
        String result = "";
        String title = intent.getStringExtra("title");
        String page = intent.getStringExtra("page");

        try{

            RegisterActivity task = new RegisterActivity();
            result = task.execute("relation",title, page).get();

        if(result == null){
            t.start();
            setContentView(R.layout.activity_sand_clock);

            ImageView sandclock = (ImageView)findViewById(R.id.sandclock);
            Glide.with(this).load(R.raw.flower).into(sandclock);
        }else {
            Log.d("ggb :", result);
            t.interrupt();
        } }
        catch(Exception e){

        }

        //웹뷰 띄워주는 페이지
//        WebView mWebView = (WebView) findViewById(R.id.webview);
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.loadUrl("file:///android_asset/www/index.html");
//        //mWebView.loadUrl("F:/Github/ggbook/MyApplication/app/src/main/assets/www/index.html");

    }
}

    class getRelation extends AppCompatActivity implements Runnable {

        getRelation(){}

        int pagenumber;
        String title;

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    RegisterActivity task = new RegisterActivity();
                    String data = (String)task.execute("relation", title, pagenumber + "").get();
                    if (data != null) {
                        wait();
                    } else {
                        WebView mWebView = (WebView)findViewById(R.id.webview);
                        mWebView.getSettings().setJavaScriptEnabled(true);
                        mWebView.loadUrl("file:///android_asset/www/index.html");
                    }
                }
            }
            catch (Exception e){
                e.getMessage();
            }
        }
    }
