
package dev.agpoon.dino.entities;

import dev.agpoon.dino.Game;
import dev.agpoon.dino.gfx.Animation;
import dev.agpoon.dino.gfx.Assets;
import dev.agpoon.dino.ui.Scoring;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    
    private Rectangle bounds;
    int rectwidth = 30, rectheight = 38, rectX = (int)x+10, rectY = (int)y+14;
    
    
    private Animation run;
    private Animation crouch;
    
    public static int width, height;
    
    int floorHeight = 150;
    int weight = 1, jumpStrength = 1;
    
    private int animSpeed;
    private int playerLevel;
    
    private Game game;
    
    public Player(Game game,int x, int y) {
        
        super(x, y);
        
        this.game = game;
        this.width = game.getWidth();
        this.height = game.getHeight();
        
        crouch = new Animation(166, Assets.dinoCrouch);
        run = new Animation(166, Assets.dinoRun);
        
        playerLevel =  1;
        
        bounds = new Rectangle(rectX, rectY, rectwidth, rectheight);
        
        
        
    }  
    
    public Rectangle getPlayerBounds(){
        return bounds;
    }
    
    @Override
    public void tick(){
        if(Scoring.getScore()>Scoring.getLimit()){
            run.addSpeed(10);
            crouch.addSpeed(10);
            Scoring.addLimit(100);
        }
        
        if(!game.getKeyManager().crouch){
            rectheight = 38;
            rectX = (int)x+10;
            rectY = (int)y+14;
        }
        
        bounds.setBounds(rectX, rectY, rectwidth, rectheight);
        run.tick();
        crouch.tick();
        
        if(game.getKeyManager().jump&&y>=floorHeight||y<floorHeight){
            
                    y-=jumpStrength;
                    jumpStrength -= weight;
                    
                     if(y>=floorHeight){
                y = floorHeight;
                jumpStrength = 13;
            }
        }
        else if(game.getKeyManager().crouch){
            rectheight = 1;
            rectY+=37;
        }
    }
    
    public void getInput(){
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentFrame(), (int)x, (int)y, null);
        //g.fillRect(rectX, rectY,rectwidth, rectheight);
    }
    
    public BufferedImage getCurrentFrame(){
        if(game.getKeyManager().crouch&&y>=floorHeight){
            return crouch.getCurrentImage();
        }
        else if(y<floorHeight){
            return Assets.dinoRun[0];
        }
        else{
            return run.getCurrentImage();
        }
    }
    
    public int getPlayerLevel(){
        return playerLevel;
    }
    
    public int getPlayerX(){
        
       return (int)x;
    }
    
    public int getPlayerY(){
        return (int)y;
    }
    
}
