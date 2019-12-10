package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class memoPopupActivity extends Activity {

    TextView titleText,memoText;
    Button updatebtn;
    String title,userid;
    RegisterActivity task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_memo_popup);

        task = new RegisterActivity();

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        userid = intent.getStringExtra("userid");

        titleText=(TextView)findViewById(R.id.titletext);
        memoText = (TextView)findViewById(R.id.memoText);
        updatebtn = (Button)findViewById(R.id.updatebtn) ;

        try{

            String pp = task.execute("getMemo",userid,title).get();
            if(!pp.equals("null")){
                memoText.setText(pp);
            }


        }
        catch(Exception e){

        }





        updatebtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        memoText.setEnabled(true);
                    }
                }
        );



    }


    //확인 버튼 클릭
    public void mOnClose(View v){

        RegisterActivity task = new RegisterActivity();
        task.execute("updateMemo",userid,title,memoText.getText()+"");

        //데이터 전달하기
        //Intent intent = new Intent();
        //setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }
    public void updateable(View v){
        memoText.setClickable(true);
        memoText.setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
