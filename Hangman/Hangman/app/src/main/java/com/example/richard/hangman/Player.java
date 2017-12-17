package com.example.richard.hangman;



public class Player {

    private String name;
    private int score;

    public Player(){
    }
    public Player(String nm, int sc){
        this.name=nm;
        this.score=sc;
    }
    public void setScore(int x) {
        this.score = x;
    }
    public int getScore() {
        return this.score;
    }
    public void setName(String x) {
        this.name = x;
    }
    public String getName() {
        return this.name;
    }

}
