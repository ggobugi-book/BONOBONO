package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {


    private ArrayList<Dic> mList;
    private Context context;
    HashMap<String,Integer> hm;
    String id;



    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView image;
        protected ProgressBar progress;
        protected TextView pagetext;



        public CustomViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.thumbnail);
            this.progress = (ProgressBar) view.findViewById(R.id.title);
            this.pagetext = (TextView)view.findViewById(R.id.pagetext);
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

        try{

            //책 제목,페이지 받아오기
            RegisterActivity task = new RegisterActivity();//서버관련해여 객체 생성
            String result = task.execute("getBookPage",title).get();//flag:getBookList를 사용하여

            final String[] titlepage = result.split(",");



            //프로그래스바 값 받아오고 넣기 시작
            float fa= hm.get(title)/(Float.parseFloat(titlepage[1])-2);
            int k = Math.round(fa*100);

            viewholder.pagetext.setText(k+"%");
            viewholder.progress.setProgress(k);
            //프로그래스바 값 받아오고 넣기 끝



            viewholder.image.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    Intent intent = new Intent(context.getApplicationContext(),myBook.class);

                    intent.putExtra("title",titlepage[0]);
                    intent.putExtra("allpage",(Integer.parseInt(titlepage[1])-2)+"");
                    intent.putExtra("filename",title);
                    intent.putExtra("page",hm.get(title).toString());
                    intent.putExtra("userid",id);


                    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));//새로운 intent를 실행하기위해서 플래그를 붙여줘야한다.

                }
            });
        }
        catch(Exception e){

        }









    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}

