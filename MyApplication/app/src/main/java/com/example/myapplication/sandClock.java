package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class sandClock extends AppCompatActivity {
    getRelation pp = new getRelation(getIntent());
    Thread th = new Thread(pp);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sand_clock);



        ImageView sandclock = (ImageView)findViewById(R.id.sandclock);
        Glide.with(this).load(R.raw.book1_edit).into(sandclock);



        th.start();




    }

    class getRelation implements Runnable{
        Intent intent;

        getRelation(Intent intent){
            this.intent = intent;
        };
        public void run(){
            try {



                String filename = intent.getStringExtra("filename");
                int page = Integer.parseInt(intent.getStringExtra("page"))+1;



                RegisterActivity task = new RegisterActivity();
                String result = task.execute("relation",filename, page+"").get();
                result = result.replace("\\","");

                if(!Thread.currentThread().isInterrupted()){
                    intent = new Intent(getApplicationContext(),RelationTest.class);
                    intent.putExtra("result",result);
                }
                startActivity(intent);
            }
            catch (Exception e){
                e.getMessage();
            }
        }
    }

    @Override
    public void onBackPressed() {
        th.interrupt();
        super.onBackPressed();
    }
}
