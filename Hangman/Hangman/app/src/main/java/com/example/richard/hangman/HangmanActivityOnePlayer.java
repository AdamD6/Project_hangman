package com.example.richard.hangman;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.richard.hangman.helpers.OnSwipeTouchListener;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class HangmanActivityOnePlayer extends AppCompatActivity{

    private TextView text;
    private Button alphabetBut;
    private int total = 26;
    private int column = 7;
    private int row = total / column;
    private String s;
    private String slovo;

    MediaPlayer mp;
    Vibrator vibe;

    private int scoreInt = 0;
    private int incorrectInt = 0;
    private int scoreWithSpace = 0;
    private int scoreIntGlobal = 0;

    private int groupOfWords;

    TextView scoreNum;
    TextView incorrectNum;
    TextView globalNum;
    GridLayout gridLayout;
    LinearLayout tableRow;
    ImageView imageView;
    RelativeLayout layout;

    Player player = new Player();

    int hangman0 = R.drawable.hangman_0;
    int hangman1 = R.drawable.hangman_1;
    int hangman2 = R.drawable.hangman_2;
    int hangman3 = R.drawable.hangman_3;
    int hangman4 = R.drawable.hangman_4;
    int hangman5 = R.drawable.hangman_5;
    int hangman6 = R.drawable.hangman_6;


    private Button[] alphabetButs;
    private TextView[] letters;

    private boolean guessed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_one);

        groupOfWords = getIntent().getIntExtra("WORDS", 1);

        setLabels();
        setLetters();
        setAlphabetLetters();

        layout = (RelativeLayout)findViewById(R.id.layoutHangmanOne);
        layout.setOnTouchListener(new OnSwipeTouchListener(HangmanActivityOnePlayer.this){
            public void onSwipeRight(){
                onBackPressed();
            }
        });
    }

    public void setLabels(){
        scoreNum = (TextView)findViewById(R.id.scoreNum);
        incorrectNum = (TextView)findViewById(R.id.incorrectNum);
        globalNum = (TextView)findViewById(R.id.globalNum);
        imageView = (ImageView)findViewById(R.id.imageView);

        imageView.setBackgroundResource(hangman0);

        scoreNum.setText(scoreInt + "");
        globalNum.setText(scoreIntGlobal+"");
        incorrectNum.setText(incorrectInt + "");
    }

    public void setLetters(){
        //inicializace slova

        slovo = getRandomWord().toUpperCase();
        letters = new TextView[slovo.length()] ;

        tableRow = (LinearLayout) findViewById(R.id.tableRow);

        for(int i = 0; i < slovo.length(); i++){
            letters[i] = new TextView(this);
            letters[i].setText(" _ ");
            letters[i].setTextSize(35);
            tableRow.addView(letters[i]);
            if(slovo.charAt(i) == ' '){
                scoreWithSpace++;
                letters[i].setText(" ");
            }
            if(slovo.charAt(i) == '-'){
                scoreWithSpace++;
                letters[i].setText(" -");
            }
        }
        Log.d("SLOVO", slovo);
    }

    public String getRandomWord(){
        int selected = groupOfWords;
        Scanner inFile = null;

        if (selected == 1) {
            inFile = new Scanner(new InputStreamReader(getResources().openRawResource(R.raw.countries)));
        }
        if (selected == 2) {
            inFile = new Scanner(new InputStreamReader(getResources().openRawResource(R.raw.animals)));
        }
        if (selected == 3) {
            inFile = new Scanner(new InputStreamReader(getResources().openRawResource(R.raw.sports)));
        }
        if (selected == 4) {
            inFile = new Scanner(new InputStreamReader(getResources().openRawResource(R.raw.adjectives)));
        }

        String token1 = "";


        ArrayList<String> temps = new ArrayList<String>();

        while(inFile.hasNext()){
            token1=inFile.nextLine();
            temps.add(token1);
        }
        inFile.close();

        String[] tempsArray = temps.toArray(new String[0]);

        Random random = new Random();

        int index = random.nextInt(tempsArray.length);
        return tempsArray[index];
    }

    public void setAlphabetLetters(){
        alphabetButs = new Button[28];
        scoreInt += scoreWithSpace;
        gridLayout= (GridLayout)findViewById(R.id.gridlayout);
        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row + 1);

        for (int i = 0; i < 28; i++) {
            final int tmp = i;
            int tmp2 = i;
            alphabetButs[i] = new Button(this);
            if(i==22 || i==23 || i==24 || i==25 || i==26){
                tmp2 = tmp2-1;
            }
            s = (""+(char)(65+tmp2));
            alphabetButs[i].setBackgroundResource(R.drawable.wood);
            alphabetButs[i].setText(s);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.height = dpToPx(51, this);
            params.width = dpToPx(49, this);
            if(i == 21 || i == 27){
                alphabetButs[i].setText("");
            }
            alphabetButs[i].setLayoutParams(params);
            alphabetButs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    char letter = (char)(65+tmp);
                    if(tmp==22 || tmp==23 || tmp==24 || tmp==25 || tmp==26){
                        letter = (char)(65+tmp-1);
                    }
                    Log.d("BUTTON", String.valueOf(letter));
                    mp = MediaPlayer.create(getApplicationContext(),R.raw.knock);
                    mp.start();
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });

                    alphabetButs[tmp].setText(" ");
                    alphabetButs[tmp].setClickable(false);
                    alphabetButs[tmp].setOnClickListener(null);

                    boolean isMatch = false;
                    for(int i = 0; i < slovo.length(); i++){
                        if(slovo.charAt(i) == letter){
                            isMatch = true;
                            letters[i].setText(""+slovo.charAt(i));
                            scoreInt++;
                            scoreNum.setText((scoreInt-scoreWithSpace)+"");
                        }
                    }
                    if(slovo.length() == scoreInt){
                        createDialogWon();
                    }
                    if(!isMatch){
                        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vibe.vibrate(50);
                        incorrectInt++;
                        incorrectNum.setText(incorrectInt+"");

                        if(incorrectInt==1) imageView.setImageResource(hangman1);
                        if(incorrectInt==2) imageView.setImageResource(hangman2);
                        if(incorrectInt==3) imageView.setImageResource(hangman3);
                        if(incorrectInt==4) imageView.setImageResource(hangman4);
                        if(incorrectInt==5) imageView.setImageResource(hangman5);
                        if(incorrectInt==6) imageView.setImageResource(hangman6);
                    }
                    if(incorrectInt==6){
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.davis);
                        mp.start();
                        resetLostGame();
                    }
                }
            });
            if(i == 21 || i == 27){
                alphabetButs[i].setOnClickListener(null);
            }
            gridLayout.addView(alphabetButs[i]);
        }

    }

    public void resetLostGame(){
        scoreIntGlobal += (scoreInt-scoreWithSpace);
        showInputDialog();
    }

    public String showInputDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("YOU LOST");
        builder.setMessage("The word was: "+ slovo
                + "\nYour final score is: "+ scoreIntGlobal
                + "\nEnter your name: ");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){

                    player.setName(input.getText().toString());
                    player.setScore(scoreIntGlobal);

                    SharedPreferences preferences = getSharedPreferences("SCORE", MODE_PRIVATE);
                    String score = preferences.getString("SCORE", "");

                    SharedPreferences.Editor preeditor = preferences.edit();

                    preeditor.putString("SCORE", score+player.getName() + " " + player.getScore() + "\n");
                    preeditor.commit();

                    createDialogLost();
            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        return input.getText().toString();
    }

    public void resetWonGame(){
        scoreIntGlobal += (scoreInt-scoreWithSpace);
        scoreInt = 0;
        scoreWithSpace = 0;

        gridLayout.removeAllViews();
        tableRow.removeAllViews();

        scoreNum.setText(""+scoreInt);
        globalNum.setText(""+scoreIntGlobal);
        incorrectNum.setText(""+incorrectInt);

        setLetters();
        setAlphabetLetters();

    }

    public void createDialogWon(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("AGAIN?");
        builder.setMessage("Do you wanna guess next word?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                resetWonGame();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                showInputDialog();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void createDialogLost(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("AGAIN?");
        builder.setMessage("Do you wanna play again?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                incorrectInt = 0;
                scoreInt = 0;
                scoreIntGlobal = 0;

                gridLayout.removeAllViews();
                tableRow.removeAllViews();
                imageView.setImageResource(hangman0);

                scoreNum.setText(""+scoreInt);
                globalNum.setText(""+scoreIntGlobal);
                incorrectNum.setText(""+incorrectInt);

                setLetters();
                setAlphabetLetters();

            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public int dpToPx(int dp, Context context){
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float)dp*density);
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
