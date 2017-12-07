package com.example.richard.hangman;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.richard.hangman.helpers.OnSwipeTouchListener;


public class Settings extends AppCompatActivity {

    Button buttonOn;
    Button buttonOff;
    Button buttonOnGlobal;
    Button buttonOffGlobal;

    RelativeLayout layout;

    int zvuk;

    AudioManager amanger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        zvuk = AudioManager.ADJUST_RAISE;


        amanger = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        buttonOn=(Button)findViewById(R.id.buttonOn);
        buttonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainMenu.music.start();
            }
        });

        buttonOff = (Button)findViewById(R.id.buttonOff);
        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainMenu.music.pause();
            }
        });

        buttonOnGlobal=(Button)findViewById(R.id.buttonOnGlobal);
        buttonOnGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zvuk += 1;
                amanger.setStreamVolume(AudioManager.STREAM_MUSIC, zvuk,0);
            }
        });

        buttonOffGlobal=(Button)findViewById(R.id.buttonOffGlobal);
        buttonOffGlobal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zvuk -= 1;
                amanger.setStreamVolume(AudioManager.STREAM_MUSIC,  zvuk, 0);
            }
        });

        layout = (RelativeLayout)findViewById(R.id.layoutSettings);
        layout.setOnTouchListener(new OnSwipeTouchListener(Settings.this){
            public void onSwipeRight(){
                onBackPressed();


            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
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
                Intent i = new Intent(getApplicationContext(), ChooseWords.class);
                startActivity(i);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                finish();
                return true;
            case R.id.Settings:
                Intent j = new Intent(getApplicationContext(), Settings.class);
                startActivity(j);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                finish();
                return true;
            case R.id.Score:
                Intent k = new Intent(getApplicationContext(), Score.class);
                startActivity(k);
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
                finish();
                return true;
            case R.id.Exit:
                System.exit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
