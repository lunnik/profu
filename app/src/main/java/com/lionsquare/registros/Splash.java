package com.lionsquare.registros;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StartAnimations();
    }

    private void StartAnimations() {

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}
