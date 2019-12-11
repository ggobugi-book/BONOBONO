package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class sandClock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sand_clock);


        ImageView sandclock = (ImageView)findViewById(R.id.sandclock);
        Glide.with(this).load(R.raw.flower).into(sandclock);


    }
}
