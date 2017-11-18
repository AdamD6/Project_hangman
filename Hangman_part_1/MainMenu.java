package com.example.richard.hangman;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.richard.hangman.helpers.OnSwipeTouchListener;

import java.util.List;

public class MainMenu extends AppCompatActivity {

    Button buttonPlay;
    View v;
    RelativeLayout layout;
    ImageView imageAnimation;
    public static MediaPlayer music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        animation();
        music();

        layout = (RelativeLayout)findViewById(R.id.relativelayout);
     /*   layout.setOnTouchListener(new OnSwipeTouchListener(MainMenu.this){
            public void onSwipeLeft(){
                chooseWords(v);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);

            }*/
        });

        /*buttonPlay=(Button)findViewById(R.id.hangmanGame);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseWords(v);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        });*/
    }

    public void animation(){
        //Animace
        AnimationDrawable animace = new AnimationDrawable();
        imageAnimation = (ImageView)findViewById(R.id.imageAnimation);

        animace.addFrame(ContextCompat.getDrawable(this, R.drawable.h1), 125);
        animace.addFrame(ContextCompat.getDrawable(this, R.drawable.h2), 125);
        animace.addFrame(ContextCompat.getDrawable(this, R.drawable.h3), 125);
        animace.addFrame(ContextCompat.getDrawable(this, R.drawable.h4), 125);
        animace.addFrame(ContextCompat.getDrawable(this, R.drawable.h5), 125);
        animace.addFrame(ContextCompat.getDrawable(this, R.drawable.h4), 125);
        animace.addFrame(ContextCompat.getDrawable(this, R.drawable.h3), 125);
        animace.addFrame(ContextCompat.getDrawable(this, R.drawable.h2), 125);
        animace.addFrame(ContextCompat.getDrawable(this, R.drawable.h1), 125);

        animace.setOneShot(false);
        imageAnimation.setBackgroundDrawable(animace);
        animace.start();
    }

    public void music(){
        //Background music
        music = MediaPlayer.create(this, R.raw.background);
        music.setLooping(true);
        music.setVolume(0.1f, 0.1f);
        music.start();
    }

    public void chooseWords(View v){
        Intent i = new Intent(getApplicationContext(), ChooseWords.class);
        startActivity(i);
    }

    public void settings(View v){
    //    Intent settings = new Intent(this, Settings.class);
    //    startActivity(settings);

    }

    public void showScore(View v){
        Intent scoreActivity = new Intent(this, Score.class);
        startActivity(scoreActivity);
    }

    public void exit(View v){

        System.exit(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Play:
                chooseWords(v);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                return true;
            case R.id.Settings:
                settings(v);
                return true;
            case R.id.Score:
                showScore(v);
                return true;
            case R.id.Exit:
                exit(v);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
