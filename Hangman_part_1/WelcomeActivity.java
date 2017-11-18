package com.example.richard.hangman;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.WelcomeTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageView image = (ImageView) findViewById(R.id.imageWelcome);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.welcome_animation);
        image.setAnimation(animation);
        animation.start();

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                startActivity(new Intent(getApplicationContext(), MainMenu.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                Handler handler = new Handler();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        i = 0;
                    }
                };
                if(i==1){
                    handler.postDelayed(r, 250);
                }else if(i == 2){
                    i = 0;
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                }

            }
        });
    }


}
