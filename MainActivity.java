package com.example.tyler.shred_v2;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private boolean splashScreen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStartUp();
    }

    private void setStartUp(){
        if(splashScreen){
            getSupportActionBar().hide();
            this.setContentView(R.layout.splashpage);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    setSplashScreen(false);
                    //getSupportActionBar().show();
                    setContentView(R.layout.activity_maps);
                }
            }, 5000);   //5 seconds
        }
        else{
            setContentView(R.layout.activity_maps);
        }
    }

    public void setSplashScreen(boolean splashScreen) {
        this.splashScreen = splashScreen;
    }
}
