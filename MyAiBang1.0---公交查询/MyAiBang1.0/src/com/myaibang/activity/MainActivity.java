package com.myaibang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);  
          
        new CountDownTimer(2000,1000) {  
              
            @Override  
            public void onTick(long millisUntilFinished) {  
            }  
            @Override  
            public void onFinish() {  
                Intent intent = new Intent();  
                intent.setClass(MainActivity.this, SecondActivity.class);  
                startActivity(intent);  
                  
                int VERSION=Integer.parseInt(android.os.Build.VERSION.SDK);  
                if(VERSION >= 5){  
                	MainActivity.this.overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);  
                }  
                finish();  
            }  
        }.start();  
    }
}