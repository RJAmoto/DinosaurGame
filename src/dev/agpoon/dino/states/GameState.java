
package dev.agpoon.dino.states;

import dev.agpoon.dino.Game;
import dev.agpoon.dino.entities.Player;
import dev.agpoon.dino.gfx.Assets;
import dev.agpoon.dino.handler.ObstacleManager;
import dev.agpoon.dino.ui.Scoring;
import dev.agpoon.dino.worlds.Background;
import dev.agpoon.dino.worlds.Obstacle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameState extends State{
    
    private float speed = 1;
    private int sec;
    
    private Player player;
    
    //Background
    private Background ground;
    private Background sky;
    private Background mountain;
    
    private ObstacleManager obstacleManager;
    private Scoring score;
 
    int rand;

    public GameState(Game game) {
        super(game);
        
        player = new Player(game, 60, 150);
        ground = new Background(Assets.ground, (int)speed*3, 200);
        sky = new Background(Assets.sky, (int)speed, 0);
        mountain = new Background(Assets.mountain, (int)(speed*1.8f), 120);
        obstacleManager = new ObstacleManager(ground);
        score = new Scoring();
        
        sec=0;

        createObstacle();
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
            game.stop();
        }

        if(sec>=50){
            
            if(((int)(Math.random()*100))>98){
            createObstacle();
                sec=0;
            }
        }
        obstacleManager.tick();

    }

    @Override
    public void render(Graphics g) {
        sky.render(g);
        mountain.render(g);
        ground.render(g);
        player.render(g);
        obstacleManager.render(g);
        g.setColor(Color.WHITE);
        score.render(g);
    }  
    
    public void setObsSpeed(){
        
    }
    
    public void createObstacle(){
        rand = (int)(Math.random()*100);
        
        if(rand<=50){
            obstacleManager.addObstacle(new Obstacle(Assets.cactus, ground.getSpeed(), 150));
        }
        else if(rand<=89&&rand>49){
            obstacleManager.addObstacle(new Obstacle(Assets.tree, ground.getSpeed(), 150));
        }
        else if(rand>90){
            obstacleManager.addObstacle(new Obstacle(Assets.pterodactyl[0], random(10)+ground.getSpeed(), 120));
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
}