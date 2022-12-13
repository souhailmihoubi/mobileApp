package com.example.covoiturage0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.airbnb.lottie.LottieAnimationView;

public class Splash extends AppCompatActivity {

    LottieAnimationView lottie,lottiebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to Remove toolBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash);

        lottie = findViewById(R.id.lottie);
        //lottiebtn = findViewById(R.id.lottiebtn);


        // lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2900);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        },4000);
    }
}