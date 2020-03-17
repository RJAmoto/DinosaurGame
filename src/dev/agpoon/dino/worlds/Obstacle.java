
package dev.agpoon.dino.worlds;

import dev.agpoon.dino.gfx.Animation;
import dev.agpoon.dino.gfx.Assets;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Obstacle extends World{
    int id;
    private Rectangle bounds;
    int rectWidth = 20, rectHeight = 30;
    
    private BufferedImage image;
    private Animation fly;
    
    public Obstacle(BufferedImage image, int speed, int y, int id){
        
        this.image = image;
        this.speed = speed;
        this.y = y;
        this.id = id;
        
        x = 500;
        bounds = new Rectangle(x+21, y+20, rectWidth, rectHeight);
        fly = new Animation(166, Assets.pterodactyl);
        
    }

    public Obstacle() {

    }
    
    public Rectangle getObsBounds(){
        return bounds;
    }

    @Override
    public void tick() {
        bounds.setBounds(x+21, y+20, rectWidth, rectHeight);
        x-=speed;
        fly.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int)x, (int)y, null);
        //g.fillRect((int)bounds.getX(), (int)bounds.getY(), rectWidth, rectHeight);
    }
    
    public void render2(Graphics g) {
        g.drawImage(getCurrentImage(), (int)x, (int)y, null);
        //g.fillRect((int)bounds.getX(), (int)bounds.getY(), rectWidth, rectHeight);
    }
    
    public BufferedImage getCurrentImage(){
        return fly.getCurrentImage();
    }
    
    
    @Override
    public float getX(){
        return x;
    }
    
    public void setX(float x){
        this.x = (int)x;
    }
    
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
}
