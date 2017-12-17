package com.example.richard.hangman;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.richard.hangman.helpers.OnSwipeTouchListener;


public class ChooseWords extends AppCompatActivity{
    int okruh = 0;
    View v;
    RelativeLayout layout1;
    RelativeLayout layout2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_words);

        layout1 = (RelativeLayout)findViewById(R.id.layoutWords);
        layout1.setOnTouchListener(new OnSwipeTouchListener(ChooseWords.this){
            public void onSwipeLeft(){
                chooseYourDestiny(v);
            }

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

    public void chooseYourDestiny(View v){
        RadioGroup rg = (RadioGroup)findViewById(R.id.RadioGroup);
        int id = rg.getCheckedRadioButtonId();

        RadioButton rbut = (RadioButton)findViewById(id);

        String group = rbut.getText().toString();


        if(group.equals("Countries"))okruh=1;
        if(group.equals("Animals"))okruh = 2;
        if(group.equals("Sports"))okruh = 3;
        if(group.equals("Adjectives"))okruh = 4;

        Toast.makeText(getApplicationContext(), "Group: " + okruh, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, ChooseNumOfPlayers.class);
        intent.putExtra("GROUP", okruh);
        startActivity(intent);
        finish();

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
