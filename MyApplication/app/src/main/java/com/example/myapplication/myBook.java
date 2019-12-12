package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;


        import android.content.Intent;
        import android.graphics.Point;
        import android.graphics.Typeface;
        import android.os.Binder;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Display;
        import android.view.MotionEvent;
        import android.view.View;

        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;

        import org.w3c.dom.Text;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;


public class myBook extends AppCompatActivity {



    int pagenumber;
    String userid;
    String title;

    AutoSave au = new AutoSave();
    Thread t = new Thread(au);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book);


        final TextView page = (TextView)findViewById(R.id.pagenumber);

        Typeface fontAwsome = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        Intent intent = getIntent();
        title = intent.getExtras().getString("filename");
        userid = intent.getExtras().getString("userid");

        Button rbtn = (Button)findViewById(R.id.relation);
        Button memobtn = (Button)findViewById(R.id.memobtn);
        final TextView page = (TextView)findViewById(R.id.pagenumber);

        rbtn.setTypeface(fontAwsome);
        memobtn.setTypeface(fontAwsome);

        t.start();



        try{
            final EditText book = (EditText)findViewById(R.id.book);

            final String allText = readFromAssets(intent.getExtras().getString("filename")+".txt");

            pagenumber = Integer.parseInt(intent.getExtras().getString("page"));

            page.setText(pagenumber+"");

            Log.d("ggb page",pagenumber+"");


            rbtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),sandClock.class);
                    intent.putExtra("title",title);
                    intent.putExtra("page",pagenumber+"");

                    startActivity(intent);
                }
            });

            memobtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),memoPopupActivity.class);

                    intent.putExtra("title",title);
                    intent.putExtra("page",pagenumber+"");
                    intent.putExtra("userid",userid);

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


            book.setText(allText.substring((pagenumber*pageStantard),(pagenumber+1)*pageStantard));//처음 페이지 보여주기

            book.setOnTouchListener(new View.OnTouchListener() {//페이지 이동하는 코드 수정해야함


                String preText = allText.substring(0,pagenumber*pageStantard);
                String currentText =allText.substring(pagenumber*pageStantard,(1+pagenumber)*pageStantard);
                String proText = allText.substring((1+pagenumber)*pageStantard);



                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    if(event.getAction() == MotionEvent.ACTION_DOWN){
                        if(event.getX()<standard && pagenumber >=1 ){


                            proText = currentText.concat(proText);
                            currentText = preText.substring(preText.length()-pageStantard);
                            preText = preText.substring(0,preText.length()-pageStantard);

                            book.setText(currentText);

                            pagenumber--;

                            page.setText(pagenumber+"");
                        }

                        else if(event.getX()>standard){
                            try{
                                preText = preText.concat(currentText);
                                currentText = proText.substring(0,pageStantard);
                                proText = proText.substring(pageStantard);

                                book.setText(currentText);
                                pagenumber++;

                                page.setText(pagenumber+"");
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
        try{
            RegisterActivity task = new RegisterActivity();
            task.execute("updatePage",userid, title,pagenumber+"");
        }
        catch(Exception e){
            e.getMessage();
        }

        t.interrupt();
        super.onBackPressed();
    }

    class AutoSave implements Runnable{

        AutoSave(){}

        public void run(){
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        RegisterActivity task = new RegisterActivity();
                        task.execute("updatePage", userid, title, pagenumber + "");
                        Thread.sleep(300000);
                    }
                }
                catch (Exception e){
                    e.getMessage();
                }
            }
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
