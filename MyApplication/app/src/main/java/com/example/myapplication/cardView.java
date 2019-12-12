package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class cardView extends AppCompatActivity {
    private ArrayList<Dic> mArrayList;
    private CustomAdapter mAdapter;


    TextView et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        String id = getIntent().getExtras().getString("id");





        try{


            RegisterActivity task = new RegisterActivity();//서버관련해여 객체 생성

            //json 받아오기 시작
            String result = task.execute("getBookList",id,"0").get();//flag:getBookList를 사용하여
            JSONObject jsnobj = new JSONObject(result);


            String jsonstr = jsnobj.getString("result");

            String jsonpage = jsnobj.getString("page");




            JSONArray jsonarr = new JSONArray(jsonstr);
            JSONArray pagearr = new JSONArray(jsonpage);



            //recycle view 설정 시작


            HashMap<String,Integer> hm = new HashMap<>();


            for(int i = 0;i<jsonarr.length();i++){
                JSONObject ob = jsonarr.getJSONObject(i);
                JSONObject pageob = pagearr.getJSONObject(i+1);
                hm.put(ob.getString("filename"),pageob.getInt(ob.getString("filename").toUpperCase()));
            }

            mArrayList = new ArrayList<>();
            mAdapter = new CustomAdapter( mArrayList,getApplicationContext(),hm,id);
            mRecyclerView.setAdapter(mAdapter);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
            mRecyclerView.addItemDecoration(dividerItemDecoration);


            for(int i = 0;i<jsonarr.length();i++){
                JSONObject ob = jsonarr.getJSONObject(i);

                Dic data = new Dic(getDrawable(R.drawable.class.getField(ob.getString("filename")).getInt(null)),ob.getString("filename"));//변수로 drawable에 접근

                mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입
                mAdapter.notifyDataSetChanged();
            }
            //recycle view 설정 끝//json 받아오기 끝
        }
        catch(Exception e){
            e.getMessage();
        }
    }

    @Override
    protected void onResume() {

        {

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);

            String id = getIntent().getExtras().getString("id");





            try{

                RegisterActivity task = new RegisterActivity();//서버관련해여 객체 생성

                //json 받아오기 시작
                String result = task.execute("getBookList",id,"0").get();//flag:getBookList를 사용하여
                JSONObject jsnobj = new JSONObject(result);


                String jsonstr = jsnobj.getString("result");

                String jsonpage = jsnobj.getString("page");




                JSONArray jsonarr = new JSONArray(jsonstr);
                JSONArray pagearr = new JSONArray(jsonpage);



                //recycle view 설정 시작


                HashMap<String,Integer> hm = new HashMap<>();


                for(int i = 0;i<jsonarr.length();i++){
                    JSONObject ob = jsonarr.getJSONObject(i);
                    JSONObject pageob = pagearr.getJSONObject(i+1);
                    hm.put(ob.getString("filename"),pageob.getInt(ob.getString("filename").toUpperCase()));
                }

                mArrayList = new ArrayList<>();
                mAdapter = new CustomAdapter( mArrayList,getApplicationContext(),hm,id);
                mRecyclerView.setAdapter(mAdapter);

                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
                mRecyclerView.addItemDecoration(dividerItemDecoration);


                for(int i = 0;i<jsonarr.length();i++){
                    JSONObject ob = jsonarr.getJSONObject(i);

                    Dic data = new Dic(getDrawable(R.drawable.class.getField(ob.getString("filename")).getInt(null)),ob.getString("filename"));//변수로 drawable에 접근

                    mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입
                    mAdapter.notifyDataSetChanged();
                }
                //recycle view 설정 끝//json 받아오기 끝
            }
            catch(Exception e){
                e.getMessage();
            }
        }

        super.onResume();
    }

}
