package com.example.richard.hangman;

import android.content.Intent;
import android.os.Bundle;
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


public class ChooseNumOfPlayers extends AppCompatActivity{

    int numberOfPlayers = 0;

    private int okruh2 = 0;

    RelativeLayout layout;

    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_num_of_players);

        okruh2 = getIntent().getIntExtra("GROUP", 1);

        layout = (RelativeLayout)findViewById(R.id.layout3);
        layout.setOnTouchListener(new OnSwipeTouchListener(ChooseNumOfPlayers.this){
            public void onSwipeLeft(){
                play(v);
            }

            public void onSwipeRight(){
                onBackPressed();
            }

        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChooseWords.class);
        startActivity(intent);
        finish();

        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    public void play(View v){
        RadioGroup rg = (RadioGroup)findViewById(R.id.RadioGroupPlayers);
        int id = rg.getCheckedRadioButtonId();

        RadioButton rbut = (RadioButton)findViewById(id);

        String group = rbut.getText().toString();

        if(group.equals("One player"))numberOfPlayers=1;
        if(group.equals("Two players"))numberOfPlayers = 2;

        Toast.makeText(getApplicationContext(), "Number: " + numberOfPlayers, Toast.LENGTH_LONG).show();
        if(numberOfPlayers == 1){
            Intent intent = new Intent(this, HangmanActivityOnePlayer.class);
            intent.putExtra("GROUP", numberOfPlayers);
            intent.putExtra("WORDS", okruh2);
            startActivity(intent);
        }
        if(numberOfPlayers == 2){
            Intent intent = new Intent(this, HangmanActivityTwoPlayers.class);
            intent.putExtra("GROUP", numberOfPlayers);
            intent.putExtra("WORDS", okruh2);
            startActivity(intent);
        }

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
