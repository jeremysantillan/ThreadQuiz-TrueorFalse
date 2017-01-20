package com.santillanj.trueorfalse;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private Switch aSwitch;
    private TextView txtScore;
    private TextView txtTime;
    private TextView txtColor;
    private LinearLayout layout;
    private Button btnTrue;
    private Button btnFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = (Switch) findViewById(R.id.mSwitch);
        txtScore = (TextView) findViewById(R.id.mTxtScore);
        txtTime = (TextView) findViewById(R.id.mTxtTimer);
        txtColor = (TextView) findViewById(R.id.mTxtColor);
        layout = (LinearLayout) findViewById(R.id.linearLayout);
        btnTrue = (Button) findViewById(R.id.mBtnTrue);
        btnFalse= (Button) findViewById(R.id.mBtnFalse);

        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String time = txtTime.getText().toString();
                int num = Integer.parseInt(time);
                num = num-1;
                time = Integer.toString(num);
                txtTime.setText(time);

            }
        };

      aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          Thread myThread;

          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked == true){

              changeLayoutColor();
                changeColorName();
              myThread  =new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 10; i>0; i--){
                            try {
                                Thread.sleep(1000);
                                handler.sendMessage(handler.obtainMessage());
                            } catch (InterruptedException e) {
                                e.printStackTrace();

                            }
                        }

                    }
                });
                myThread.start();
            }
              else{
                myThread.interrupt();
                handler.removeCallbacks(myThread);
            }
          }
      });

    }
    public int changeLayoutColor(){
        Random r = new Random();
        int num = r.nextInt(6 - 1) + 1;
        switch (num){
            case 1: //red
                layout.setBackgroundColor(Color.RED);
                break;
            case 2: //yellow
                layout.setBackgroundColor(Color.YELLOW);
                break;
            case 3: //blue
                layout.setBackgroundColor(Color.BLUE);
                break;
            case 4: //green
                layout.setBackgroundColor(Color.GREEN);
                break;
            case 5: //magenta
                layout.setBackgroundColor(Color.MAGENTA);
                break;
        }
        return num;
    }

    public int changeColorName(){
        Random r = new Random();
        int num = r.nextInt(6 - 1) + 1;
        switch (num){
            case 1: //red
              txtColor.setText("RED");
                break;
            case 2: //yellow
                txtColor.setText("YELLOW");
                break;
            case 3: //Blue
                txtColor.setText("BLUE");
                break;
            case 4: //green
                txtColor.setText("GREEN");
                break;
            case 5: //magenta
                txtColor.setText("MAGENTA");
                break;
        }
        return num;
    }

}
