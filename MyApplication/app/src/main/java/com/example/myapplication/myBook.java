package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Point;
        import android.os.Binder;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Display;
        import android.view.MotionEvent;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.EditText;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.StringReader;

public class myBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book);

        Intent intent = getIntent();

        Button rbtn = (Button)findViewById(R.id.relation);

        try{

            final EditText book = (EditText)findViewById(R.id.book);

            final String allText = readFromAssets(intent.getExtras().getString("filename")+".txt");
            final int page = Integer.parseInt(intent.getExtras().getString("page"));

            Log.d("ggb page",page+"");


            rbtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),RelationTest.class);
                    startActivity(intent);
                }
            });




            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            final int standard = width/2;
            final int pageStantard = 350;


            book.setText(allText.substring((page*pageStantard),(page+1)*pageStantard));//처음 페이지 보여주기

            book.setOnTouchListener(new View.OnTouchListener() {//페이지 이동하는 코드 수정해야함
                int count = page;

                String preText = allText.substring(0,count*pageStantard);
                String currentText =allText.substring(count*pageStantard+1,(1+count)*pageStantard);
                String proText = allText.substring((1+count)*pageStantard);



                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == MotionEvent.ACTION_DOWN){
                        if(event.getX()<standard && count >1 ){

                            count--;

                            proText = currentText.concat(proText);

                            currentText = preText.substring(preText.length()-pageStantard);
                            preText = preText.substring(0,preText.length()-pageStantard);

                            book.setText(currentText);
                        }
                        else if(event.getX()>standard){
                            try{
                                preText = preText.concat(currentText);
                                currentText = proText.substring(0,pageStantard);
                                proText = proText.substring(pageStantard);

                                book.setText(currentText);
                                count++;
                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                    return true;
                }
            });

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onBackPressed() {
        Log.d("ggb","여기에 페이지 업데이트");
        super.onBackPressed();
    }



    private String readFromAssets(String filename) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(filename)));

        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while(line != null) {
            sb.append("\n");
            sb.append(line);
            line = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }
}
