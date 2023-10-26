package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;
import android.window.SplashScreen;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreenMain extends AppCompatActivity {

    private static final long SPLASH_SCREEN_TIME_OUT = 2650;
    TextView appname,apptag;
    LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This method is used so that your splash activity
        //can cover the entire screen.
        apptag=findViewById(R.id.txtview2);
        appname = findViewById(R.id.textView);
        lottie =findViewById(R.id.registerAnimation);

        appname.animate().translationY(1500).setDuration(5000).setStartDelay(0);
        apptag.animate().translationY(1500).setDuration(5000).setStartDelay(0);
        lottie.animate().translationX(-2000).setDuration(4000).setStartDelay(500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        },2650);
        //this will bind your MainActivity.class file with activity_main.
    }
}