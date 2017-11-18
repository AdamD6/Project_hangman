package com.example.richard.hangman;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.example.richard.hangman.helpers.OnSwipeTouchListener;

public class Score extends AppCompatActivity{
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        TextView textview = (TextView)findViewById(R.id.VypisHracu);
        SharedPreferences preferences = getSharedPreferences("SCORE", MODE_PRIVATE);
        String vysl = preferences.getString("SCORE", "");
        textview.setText(vysl);

        layout = (RelativeLayout)findViewById(R.id.scoreLayout);
  /*      layout.setOnTouchListener(new OnSwipeTouchListener(Score.this){
            public void onSwipeRight(){
                onBackPressed();


            }*/
        });
    }

    public void deleteScore(View v){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(true);
        dialog.setMessage("Smažou se veškeré dosažené výsledky! OPRAVDU?");
        dialog.setPositiveButton("Ano",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences preferences = getSharedPreferences("SCORE", MODE_PRIVATE);
                        SharedPreferences.Editor preeditor = preferences.edit();
                        preeditor.putString("SCORE", "");
                        preeditor.commit();
                        finish();
                    }
                });
        dialog.setNegativeButton("Ne",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog start = dialog.create();
        start.show();
    }


    public void back(View v){

        finish();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

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
       //         Intent j = new Intent(getApplicationContext(), Settings.class);
       //         startActivity(j);
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