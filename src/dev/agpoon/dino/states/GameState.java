
package dev.agpoon.dino.states;

import dev.agpoon.dino.Game;
import dev.agpoon.dino.entities.Player;
import dev.agpoon.dino.gfx.Assets;
import dev.agpoon.dino.handler.ObstacleManager;
import dev.agpoon.dino.ui.Scoring;
import dev.agpoon.dino.worlds.Background;
import dev.agpoon.dino.worlds.Obstacle;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameState extends State{
    
    private Color color;
    
    private Color backColor;
    public static boolean gameOver;
    private float speed = 1;
    private int sec;
    
    int a = -40;
    
    private Player player;
    
    //Background
    private Background ground;
    private Background sky;
    private Background mountain;
    
    private ObstacleManager obstacleManager;
    private Scoring score;
 
    int rand;
    
    private Rectangle restart = new Rectangle((game.getWidth()/2)-110, 160, 100, 30);
    private Rectangle back = new Rectangle((game.getWidth()/2)+15, 160, 100, 30);

    public GameState(Game game) throws IOException {
        
        super(game);
        gameOver = false;
        player = new Player(game, 60, 150);
        ground = new Background(Assets.ground, (int)speed*3, 200);
        sky = new Background(Assets.sky, (int)speed, 0);
        mountain = new Background(Assets.mountain, (int)(speed*2), 120);
        obstacleManager = new ObstacleManager(ground);
        score = new Scoring();
        
        sec=0;
        
        color = Color.WHITE;
        
    }
    
    public boolean collision(){
        
        for(int x = 0; x < obstacleManager.getRectList().size(); x++){
            if(player.getPlayerBounds().intersects((Rectangle)obstacleManager.getRectList().get(x))){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
       
    @Override
    public void tick() {

        if(!gameOver){
        sec++;
        mountain.tick();
        sky.tick();
        ground.tick();
        player.tick();
        score.tick();
        
        if(Scoring.getScore()>Scoring.getLimit()){
            ground.addSpeed(1);
            sky.addSpeed(1);
            mountain.addSpeed(1);
        }
        
        if(collision()){
                GameState.setGameOver(true);
        }

        if(sec>=50){
            
            if(((int)(Math.random()*100))>98){
            createObstacle();
                sec=0;
            }
        }
        obstacleManager.tick();
        }
        else if(gameOver){
            if(Scoring.getScore()>score.getHighscore()){
                score.setHighscore();
                score.newHighScore();
            }
            
            obstacleManager.tick();
            //RESTART BUTTON
                if(game.getMouseManager().getMx()>=140&&game.getMouseManager().getMx()<=240
                    &&game.getMouseManager().getMy()>=159&&game.getMouseManager().getMy()<=190||game.getKeyManager().jump){
            
                shiftGray();
            
                if(game.getMouseManager().isLeftPressed()||game.getKeyManager().jump){
                    
                game.setGame();
                obstacleManager.restart();
                ground.restart((int)speed*3);
                sky.restart((int)speed);
                mountain.restart((int)speed*2);
                score.restartLimit();
                player.restart();
                
                a = 0;
                }
            }
                //BACK BUTTON
                else if(game.getMouseManager().getMx()>=265&&game.getMouseManager().getMx()<=365
                    &&game.getMouseManager().getMy()>=160&&game.getMouseManager().getMy()<=190){
                    backGray();
                    
                    if(game.getMouseManager().isLeftPressed()){
                        try{
                            Thread.sleep(100);
                            game.setMenu();
                            GameState.gameOver = false;
                            obstacleManager.restart();
                            a = 0;
                        } catch (InterruptedException ex) {

                        }
                        
                    }
            }
            else{
                shiftWhite();
                backWhite();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        
        if(!gameOver){
            sky.render(g);
            mountain.render(g);
            ground.render(g);
            player.render(g);
            obstacleManager.render(g);
                        
            g.setColor(Color.WHITE);
            score.render(g);
        }
        else if(gameOver){
            int newSkyX = (int)sky.getX();
            int newMountainX = (int)mountain.getX();
            int newMountainY = (int)mountain.getY();
            int newGroundX = (int)ground.getX();
            int newGroundY = (int)ground.getY();
            int newPlayerX = player.getPlayerX();
            int newPlayerY = player.getPlayerY();
           
            score.stop();
           
            g.drawImage(Assets.sky, newSkyX, 0, null);
            g.drawImage(Assets.mountain, newMountainX, newMountainY, null);
            g.drawImage(Assets.ground, newGroundX, newGroundY, null);
            g.drawImage(Assets.dinoRunGameOver, newPlayerX, newPlayerY, null);
            obstacleManager.render(g);
           
           g.setColor(Color.WHITE);
           score.render(g);
           
           if(a<120){
                a+=speed;
           }
           else{
                a = 120;
                
                g.setColor(color);
                Graphics2D g2d = (Graphics2D) g;
                g.setFont(new Font("Alien Wars", Font.BOLD, 20));
                g2d.draw(restart);
                g.drawString("RESTART", restart.x+10,restart.y+23);
                
                g.setColor(backColor);
                g.drawString("BACK", back.x+25,back.y+23);

                g2d.draw(back);
         
           }
           
           g.setColor(Color.RED);
           g.setFont(new Font("Forte", Font.BOLD, 70));
           a+=speed;
           g.drawString("You Lose", 100, a);
           
        }
    }  
    
    public void setObsSpeed(){
        
    }
    
    public void createObstacle(){
        rand = (int)(Math.random()*100);
        
        if(rand<=50){
            obstacleManager.addObstacle(new Obstacle(Assets.cactus, ground.getSpeed(), 150, 2));
        }
        else if(rand<=89&&rand>49){
            obstacleManager.addObstacle(new Obstacle(Assets.tree, ground.getSpeed(), 150, 2));
        }
        else if(rand>90){
            obstacleManager.addObstacle(new Obstacle(Assets.pterodactyl[0], random(10)+ground.getSpeed(), 120,3));
        }
    }
    
    public int random(int rand){
        int a = (int)(Math.random()*rand);
        
        if(a<3){
            return 4;
        }
        else{
            return a;
        }
    }
    public static void setGameOver(boolean gameOver){
        GameState.gameOver = gameOver;
    }
    
    public static boolean getGameOver(){
        return gameOver;
    }
    
    private void shiftGray() {
         color = Color.GRAY;
    }

    private void shiftWhite() {
        color = Color.WHITE;
    }
    private void backGray() {
         backColor = Color.GRAY;
    }

    private void backWhite() {
        backColor = Color.WHITE;
    }
}