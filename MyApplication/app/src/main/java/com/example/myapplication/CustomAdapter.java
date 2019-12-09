package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {


    private ArrayList<Dic> mList;
    private Context context;
    HashMap hm;
    String id;



    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView image;
        protected TextView title;


        public CustomViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.thumbnail);
            this.title = (TextView) view.findViewById(R.id.title);

        }
    }

    public CustomAdapter(ArrayList<Dic> list, Context context, HashMap hm,String id) {
        this.mList = list;
        this.context = context;
        this.hm = hm;
        this.id = id;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.image.setImageDrawable(mList.get(position).getImage());
        final String title = mList.get(position).getTitle();


        viewholder.title.setText("");


        viewholder.image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent intent = new Intent(context.getApplicationContext(),myBook.class);

                intent.putExtra("filename",title);
                intent.putExtra("page",hm.get(title).toString());
                intent.putExtra("userid",id);


                context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));//새로운 intent를 실행하기위해서 플래그를 붙여줘야한다.

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}

