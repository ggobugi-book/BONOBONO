package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class joinActivity extends AppCompatActivity {

    Button check;
    EditText userid;
    TextView txtResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        check = (Button)findViewById(R.id.check);
        userid = (EditText) findViewById(R.id.userid);



        check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                try {
                    String result;
                    String id = userid.getText().toString();
                    String pw = "1";

                    RegisterActivity task = new RegisterActivity();
                    result = task.execute("login",id, pw).get();

                    if(result.equals("1")){
                        Intent intent1 = new Intent(getApplicationContext(),PopupActivity.class);
                        intent1.putExtra("data", "존재하는 아이디입니다.");
                        startActivity(intent1);
                    }
                    else{
                        Intent intent2= new Intent(getApplicationContext(),joinActivity2.class);//아이디 없으면 조인 2로 이동
                        intent2.putExtra("userid",id);
                        startActivity(intent2);
                    }

                } catch (Exception e) {
                    Log.i("DBtest", "…..ERROR…..!");
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //데이터 받기
                String result = data.getStringExtra("result");
                txtResult.setText(result);
            }
        }

    }
}