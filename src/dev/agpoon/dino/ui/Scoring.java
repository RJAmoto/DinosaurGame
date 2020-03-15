package dev.agpoon.dino.ui;

import java.awt.Font;
import java.awt.Graphics;

public class Scoring extends UI{
    
    private static int score;
    private static int limit;
    
    private long LastTime, timer;
    private int speed = 200;
    Font font;
    
    public Scoring(){
        
        score = 0;
        limit = 100;
        
        LastTime = System.currentTimeMillis();
        
    }

    @Override
    public void tick() {
        timer+=System.currentTimeMillis()-LastTime;
        LastTime = System.currentTimeMillis();
        if(timer>speed){
            score+=1;
            timer = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setFont(new Font("Calibri", Font.BOLD, 20));
        g.drawString("SCORE: "+Integer.toString(score), 390, 30);
    }
    
    public static int getScore(){
        return score;
    }
    
    public static int getLimit(){
        return limit;
    }
    
    public static void addLimit(int limit){
        Scoring.limit += limit;
    }
    
}